package com.able.ableSD.Contorller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.able.ableSD.SolutionCode;
import com.able.ableSD.DTO.SolutionCodeMap;
import com.able.ableSD.DTO.SolutionFiles;
import com.able.ableSD.DTO.User;
import com.able.ableSD.Service.SolutionFilesMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.apache.http.ssl.SSLContextBuilder;
import org.json.JSONObject;

@Controller
public class HomeController {

	@Autowired
	private SolutionFilesMapper solutionFilesMapper; // SQL 맵핑 클래스
	private static LinkedHashMap<String, List<SolutionFiles>> solutionMap;// key : 솔루션 이름 List : 솔루션 설치 파일 리스트

	/////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/*
	 * 처음화면은 로그인 화면
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		return "login";
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////

	/*
	 * 로그인 성공 시, 다운로드 가능한 홈 화면으로 이동
	 */
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String goDownload(HttpServletRequest request) {
		
		SolutionCodeMap solutionCodeMap = SolutionCodeMap.getInstance();

		HttpSession session = request.getSession(); // 세션을 가져옴
		
		//현재 로그인 중이지 않거나 세션이 없으면 로그인이 필요하다는 메시지를 보냄
		if (session.getAttribute("currentUser") == null || !request.isRequestedSessionIdValid()) {
			request.setAttribute("loginMessage", "로그인이 필요합니다.");	
		} 
		
		User currentUser = (User) session.getAttribute("currentUser");
		if(currentUser != null && (currentUser.getAccount().equals("") || currentUser.getAccount()==null)) {
			request.setAttribute("loginMessage", "로그인이 필요합니다.");	
		}
		
		//현재 로그인 중 상태
		else {
			solutionMap = new LinkedHashMap<>(); //솔루션 파일 리스트를 담을 맵 객체 생성
			
			//솔루션 코드와 이름이 선언된 enum객체 순회
			// enum SolutionCode : XContact("BA12"), XCube("BA04"), TypeIX("BA02"), AbleReport("BA15"), AbleWallboard("BA08");
			for (SolutionCode solutionCode : SolutionCode.values()) {
				
				String code = solutionCode.getCode(); // 솔루션 코드를 가져옴
				
				solutionMap.put(code, solutionFilesMapper.selectSolution(code)); // 코드를 key로 하고, 코드를 이용해셔 DB에서 파일리스트를 가져와 value로 저장

			}
			
			solutionMap.put("BA99", null);
			
			
			//li태그의 끝을 맞추기 위해 각 섹션 ( 서버 프로세스, 운영 프로그램, 메뉴얼)의 가장 많은 개수를 찾는다.
			List<Integer> countMaxLines = solutionFilesMapper.countMaxLines();
			
			request.setAttribute("solutionMap", solutionMap);
			
			request.setAttribute("countMaxLines", countMaxLines);
			
			request.setAttribute("solutionCodeMap", solutionCodeMap.getMap());

		}
		return "home";
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/*
	 * retrieve API 호출 : LoginAPI 호출하기 전 먼저 retrieveAPI를 호출해야함.
	 */
	@RequestMapping(value = "/retrieveAPI", method = RequestMethod.GET)
	public ResponseEntity<String> retrieveAPI()
			throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException, IOException {
		
		String apiUrl = "https://192.168.10.38:5001/webapi/query.cgi?api=SYNO.API.Info&version=1&method=query&query=SYNO.API.Auth,SYNO.FileStation";//retrieveAPI url

		CloseableHttpClient httpClient = getHttpClient(); // 통신을 위한 HttpClient객체
		
		HttpGet httpGet = new HttpGet(apiUrl); // apiUrl로 Get요청 생성

		try {
			String responseStr = getResultHttpGet(httpClient, httpGet); // HttpClient로 api를 호풀하고 결과를 String으로 받아오

			httpClient.close(); //httpClient를 닫아줌
			
			return new ResponseEntity<>(responseStr.toString(), HttpStatus.OK); 
		
		} catch (IOException e) {
			e.printStackTrace();

			httpClient.close(); //httpClient를 닫아줌
			
			return new ResponseEntity<>("API 호출 실패: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/*
	 * Login API 호풀
	 */
	@RequestMapping(value = "/loginAPI", method = RequestMethod.POST)
	public ResponseEntity<String> loginAPI(HttpServletRequest request, @RequestParam("account") String account, @RequestParam("passwd") String passwd)
			throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException, IOException {
		
		String apiUrl = "https://192.168.10.38:5001/webapi/auth.cgi?api=SYNO.API.Auth&version=3&method=login&account="
				+ account + "&passwd=" +  URLEncoder.encode(passwd, "UTF-8") + "&session=FileStation&format=cookie"; // Login API Url : account(아이디)와 passwd(비밀번호)를 url에 파라미터로 줌

		CloseableHttpClient httpClient = getHttpClient(); // 통신을 위한 HttpClient객체 
		
		HttpGet httpGet = new HttpGet(apiUrl); // apiUrl로 Get요청 생성
		
		try {
			
			String responseStr = getResultHttpGet(httpClient, httpGet); // HttpClient로 api를 호풀하고 결과를 String으로 받아오
			
			JSONObject jsonObj = new JSONObject(responseStr);// 스트링을 json형태로 변환
			
			boolean success = jsonObj.getBoolean("success"); // 로그인 ㄴ결과를 success(키 값)으로 가져옴
			
			String returnString = ""; // jsp로 보낼 결과 문자열
			
			if (success) { // 로그인에 성공				
				JSONObject dataObj = jsonObj.getJSONObject("data"); // data를 키 값으로 json에서 data Object를 가져옴
				
				String sid = dataObj.getString("sid"); // dataObj에서 sid를 가져옴
				
				User currentUser = new User(account, passwd, sid); // user객체 생성
				
				request.getSession().setAttribute("currentUser", currentUser); // 세션에 user 객체 저장
			
				returnString = "success"; // jsp에 보낼 returnString에 success 저장
			
			} else { // 로그인에 실패
				JSONObject errorObj = jsonObj.getJSONObject("error");  // error를 키 값으로 json에서 error Object를 가져옴
				
				int errorCode = errorObj.getInt("code"); // error Obj에서 에러코드를 가져옴
				
				returnString = +errorCode + ""; // jsp에 보낼 returnString에 에러코드 저장
			}
			
			httpClient.close(); //httpClient를 닫아줌
			
			return new ResponseEntity<>(returnString, HttpStatus.OK); // 로그인 결과에 상관업싱 api는 호출 성공했으므로 OK코드를 보냄 
		
		} catch (IOException e) { // api 호출 실패
			e.printStackTrace();
	
			httpClient.close(); //httpClient를 닫아줌
			
			return new ResponseEntity<>("API 호출 실패: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	
		} 
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/*
	 * Logout API 로출 
	 */
	@RequestMapping(value = "/logoutAPI", method = RequestMethod.POST)
	public ResponseEntity<String> logoutAPI(HttpServletRequest request)
			throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException, IOException {

		HttpSession session = request.getSession(); // 현재 세션을 가져옴
		
		String returnString = ""; // jsp로 보낼 결과 문자열
		
		CloseableHttpClient httpClient = getHttpClient(); // 통신을 위한 HttpClient객체
		
		if (session != null) { // 현제 세션이 null이 아니면 -> 로그인 상태
			User currentUser = (User) session.getAttribute("currentUser"); // 세션에 저장된 현재 유저 객체를 가져옴
			
			String sid = currentUser.getSid(); // 유저의 sid를 가져옴
			
			String apiUrl = "https://192.168.10.38:5001/webapi/auth.cgi?api=SYNO.API.Auth&version=1&method=logout&_sid="
					+ sid + "&session=FileStation"; // Logout API Url : 유저의 sid를 파라미터로 줌

			
			
			HttpGet httpGet = new HttpGet(apiUrl); // apiUrl로 Get요청 생성

			try {
				getResultHttpGet(httpClient, httpGet);
				
			} catch (IOException e) { // api 호출 실패
				e.printStackTrace();
				
				httpClient.close(); //httpClient를 닫아줌
				
				return new ResponseEntity<>("API 호출 실패: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
			session.invalidate(); // 세션 종료
			
			returnString = "success"; // jsp에 보낼 returnString에 success 저장
		} else {
			returnString = "fail"; // jsp에 보낼 returnString에 fail 저장
		} 
		
		httpClient.close(); //httpClient를 닫아줌
		
		return new ResponseEntity<>(returnString, HttpStatus.OK); 

	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/*
	 * Download API 호출
	 */
	@RequestMapping(value = "/downloadAPI", method = RequestMethod.GET)
	public ResponseEntity<byte[]> downloadAPI(HttpServletRequest request) {
		
		String errMessage = "";
		
		User currentUser = (User) request.getSession().getAttribute("currentUser"); // 세션으로부터 현재 유저 객체를 가져옴
	
		if(currentUser == null || currentUser.getSid() == null) { // 현재 유저 정보가 없거나 sid가 없으면 에롴드 302를 리턴
	        return new ResponseEntity<>(HttpStatus.FOUND); 
	    }
		
		String FILE_NAME = request.getParameter("FILE_NAME"); //파일이름
		
		String FILE_PATH = request.getParameter("FILE_PATH"); //파일경로
		//String FILE_PATH = "/※기술본부/솔루션/X-Contact/실행파일/ablePDS.zip"; //test path 
		//String FILE_PATH = "/※기술본부/솔루션/XCUBE-CTI/BasicDataCreator/XCUBE_BasicDataCreator.zip"; // test path
		
		String SOLUTION_CD = request.getParameter("SOLUTION_CD"); // 솔루션 분류 코드
		
		String fileNameWithExt = FILE_PATH.substring(FILE_PATH.lastIndexOf("/") + 1); // 확장자가 포함된 파일 이름을 파일 경로에서 추출
		
		int dotIndex = fileNameWithExt.lastIndexOf("."); // 파일 확장자를 구하기 위한 .의 index
		
		String fileName = fileNameWithExt.substring(0, dotIndex); // 확장자가 포함된 파일 이름에서 .의 index로 파일이름 추출
		
		String fileExt = fileNameWithExt.substring(dotIndex + 1); // 확장자가 포함된 파일 이름에서 .의 index로 파일확장자 추출
		
		fileName = fileName + "." + fileExt; // 파일이름 
		
		String mode = "open"; // api url mode
		  
		try {
			String sid = currentUser.getSid(); // 현재 유저의 sid
			
			String apiUrl = "https://192.168.10.38:5001/webapi/entry.cgi?api=SYNO.FileStation.Download&version=2&method=download"; // Download API Url
			
			if (FILE_PATH == null || FILE_PATH.equals("")) { // 경로가 존재하지 않을 때
				errMessage = "다운로드 경로가 존재하지 않습니다.";
				
				return new ResponseEntity<>(errMessage.getBytes(), HttpStatus.NO_CONTENT); // 경로가 없으면 에러코드 204를 리턴
			}

			String url = apiUrl + "&path=" + URLEncoder.encode(FILE_PATH, "UTF-8") + "&mode=" + mode + "&_sid=" + sid; // path는 인코딩해야 함. mode는 open

			CloseableHttpClient httpClient = getHttpClient(); // 통신을 위한 HttpClient객체

			HttpGet httpGet = new HttpGet(url); // apiUrl로 Get요청 생성
			
			HttpResponse httpResponse = httpClient.execute(httpGet);  // execute로 get요청을 보내고 실행 결과를 HttpResponse객체에 저장함

			int httpStatusCode = httpResponse.getStatusLine().getStatusCode(); // 호출 결과 코드를 가져옴

			if (httpStatusCode == HttpStatus.OK.value()) {
				//HttpResponse response = httpClient.execute(httpGet);
				HttpEntity entity = httpResponse.getEntity(); // 실행 결과를 저장한 HttpResponse에서 Entity(내용)를 가져옴
				
				InputStream inputStream = entity.getContent(); // Entity를 바이트스트림으로 가져옴
				
				byte[] fileBytes = IOUtils.toByteArray(inputStream); // 바이트 스트림을 바이트 배열로 변환
				
				HttpHeaders headers = new HttpHeaders(); // HTTP 응답 헤더를 설정하고 관리하는 HttpHeaders 객체 생성
				
				headers.setContentDispositionFormData("attachment", URLEncoder.encode(fileName, "UTF-8")); // HTTP 응답 헤더에 attachment타입의 첨부 파일로 다운로드, 파일 이름을 UTF-8로 인코딩해서 헤더에 추가.

				String ST_DATE = getCurrentDateTime().split(" ")[0].trim(); //  현재 날짜
				
				String ST_TIME = getCurrentDateTime().split(" ")[1].trim(); // 현재 시간
				
				String USER_NM = currentUser.getAccount(); // 유저의 아이디
				
				String WORK_TYPE = "F005"; // 다운로드 : F005, 업로드 : F006
				 
				solutionFilesMapper.insertHistory(SOLUTION_CD,FILE_NAME,ST_DATE, ST_TIME, USER_NM, WORK_TYPE); // 다운로드 성공 시, DB에 로그 저장
				
				httpClient.close(); //httpClient를 닫아줌
				
				return new ResponseEntity<byte[]>(fileBytes, headers, HttpStatus.OK);

			} else {
				httpClient.close(); //httpClient를 닫아줌
				
				errMessage = "다운로드 실패: " + httpResponse.getStatusLine().getStatusCode();
	
				return new ResponseEntity<>(errMessage.getBytes(), HttpStatus.NOT_FOUND);
			}
		} catch (IOException | KeyManagementException | NoSuchAlgorithmException | KeyStoreException e) {
			
			errMessage = "다운로드 실패: " + e.getMessage();
		
			return new ResponseEntity<>(errMessage.getBytes(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/*
	 * 현재 시간과 날짜를 구하는 메소드
	 */
	private static String getCurrentDateTime() {
		Date currentDate = new Date();
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd HHmmss");
		
		String formattedDateTime = dateFormat.format(currentDate);
		
		return formattedDateTime;
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/*
	 * api를 호풀하기 위한 Apache HttpClient를 얻는 메소드.
	 * CloseableHttpClient : 자동으로 리소르를 관리 가능한 HttpClient.
	 * custom() :  HttpClient를 구성하는 다양한 옵션을 설정.
	 * setSslcontext() :  SSL/TLS 연결을 설정하는 메서드. 
	 * SSLContextBuilder() : SSLContextBuilder클래스를 사용하여 SSL 컨텍스트를 빌드.
	 * loadTrustMaterial() : 신뢰할 수 있는 인증서를 로드.
	 * TrustSelfSignedStrategy :  Apache HttpClient 라이브러리에서 사용되는 인증 전략 중 하나로, 자체 서명된 (self-signed) SSL/TLS 인증서를 신뢰하는 전략
	 * setSSLHostnameVerifier() : 호스트 이름 확인을 비활성화하는 설정으로  SSL/TLS 연결에서 호스트 이름 일치를 확인하지 않음.  (  주로 자체 서명된 인증서를 사용할 때 호스트 이름 확인을 무시하려고 할 때 사용 )
	 */
	private CloseableHttpClient getHttpClient() throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException {		
		return HttpClients.custom()
				.setSslcontext(new SSLContextBuilder().loadTrustMaterial(new TrustSelfSignedStrategy()).build())
				.setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE).build();

	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/*
	 * HttpClient로 api를 호풀하고 결과를 String으로 받아오는 메소드
	 */
	private String getResultHttpGet(CloseableHttpClient httpClient, HttpGet httpGet)
			throws IOException, ClientProtocolException {		
		HttpResponse response = httpClient.execute(httpGet); // execute로 get요청을 보내고 실행 결과를 HttpResponse객체에 저장함
		
		HttpEntity entity = response.getEntity(); // 실행 결과를 저장한 HttpResponse에서 Entity(내용)를 가져옴
		
		InputStream is = entity.getContent(); // Entity를 바이트스트림으로 가져옴
		
		 // 바이트 배열을 저장하기 위한 ByteArrayOutputStream 생성
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int bytesRead;
        
        while ((bytesRead = is.read(buffer)) != -1) {
            byteArrayOutputStream.write(buffer, 0, bytesRead);
        }
        
        // 바이트 배열을 문자열로 변환
        return new String(byteArrayOutputStream.toByteArray(), StandardCharsets.UTF_8);
		
		/*
		byte[] bytes = is.readAllBytes(); // 바이트배열로 받음
		
		return new String(bytes, StandardCharsets.UTF_8);// 스트링으로 변환
		*/
	}

}
