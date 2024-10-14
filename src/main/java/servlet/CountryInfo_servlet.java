package servlet;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import com.soap.ws.client.countryInfo.CountryInfoService;
import com.soap.ws.client.countryInfo.CountryInfoServiceSoapType;
import com.soap.ws.client.countryInfo.TCountryInfo;
import com.soap.ws.client.countryInfo.TCurrency;

import ClientWS_Travel_Guide.CI_Service;

@WebServlet("/CountryInfo_servlet")
public class CountryInfo_servlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		
		String countryISOCode = request.getParameter("isoCode");

		CI_Service cis = new CI_Service();

		Gson gson = new Gson();
		
        String countryName = request.getParameter("name");

        if (countryISOCode != null && !countryISOCode.isEmpty()) {
            CountryInfoService ci_service = new CountryInfoService();
            CountryInfoServiceSoapType ci_serviceSOAP = ci_service.getCountryInfoServiceSoap();

            TCountryInfo countryInfo = ci_serviceSOAP.fullCountryInfo(countryISOCode);

            if (countryInfo != null && countryInfo.getSName() != null) {
                TCurrency currencyInfo = ci_serviceSOAP.countryCurrency(countryISOCode);
                Map<String, Object> countryDetails = new HashMap<>();
                countryDetails.put("name", countryInfo.getSName());
                countryDetails.put("capital", countryInfo.getSCapitalCity());
                countryDetails.put("currencyName", currencyInfo.getSName());
                countryDetails.put("currencyCode", currencyInfo.getSISOCode());
                countryDetails.put("phoneCode", countryInfo.getSPhoneCode());
                countryDetails.put("flag", countryInfo.getSCountryFlag());
                
                String jsonResponse = gson.toJson(countryDetails);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(jsonResponse);
            } else {
                response.getWriter().write("null"); 
            }
        } else if (countryName != null && !countryName.isEmpty()) {
            CountryInfoService ci_service = new CountryInfoService();
            CountryInfoServiceSoapType ci_serviceSOAP = ci_service.getCountryInfoServiceSoap();

            String isoCode = ci_serviceSOAP.countryISOCode(countryName);

            if (isoCode != null && !isoCode.isEmpty()) {
                response.setContentType("text/plain");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(isoCode);
            } else {
                response.getWriter().write("null"); 
            }
        } else {
		
		
		
		if (countryISOCode != null && !countryISOCode.isEmpty()) {
		    TCountryInfo countryInfo = new TCountryInfo();

			CountryInfoService ci_service = new CountryInfoService();
			CountryInfoServiceSoapType CI_serviceSOAP = ci_service.getCountryInfoServiceSoap();
			countryInfo = CI_serviceSOAP.fullCountryInfo(countryISOCode);
			
			
			TCurrency currencyInfo = new TCurrency();
			currencyInfo = CI_serviceSOAP.countryCurrency(countryISOCode);

		    Map<String, Object> countryDetails = new HashMap<>();
		    countryDetails.put("name", countryInfo.getSName());
		    countryDetails.put("capital", countryInfo.getSCapitalCity());
		    countryDetails.put("currencyName", currencyInfo.getSName());
		    countryDetails.put("currencyCode", countryInfo.getSISOCode());
		    countryDetails.put("phoneCode", countryInfo.getSPhoneCode());
		    countryDetails.put("flag", countryInfo.getSCountryFlag());

		    String jsonResponse = gson.toJson(countryDetails);

		    response.setContentType("application/json");
		    response.setCharacterEncoding("UTF-8");
		    response.getWriter().write(jsonResponse);
		} else {
		
	        String jsonCurrencies = gson.toJson(cis);
	
	        response.setContentType("application/json");
	        response.setCharacterEncoding("UTF-8");
	        response.getWriter().write(jsonCurrencies);
		}
	}
	}
}






