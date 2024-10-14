package ClientWS_Travel_Guide;
import java.util.List;
import com.soap.ws.client.countryInfo.ArrayOftContinent;
import com.soap.ws.client.countryInfo.ArrayOftCountryCodeAndName;
import com.soap.ws.client.countryInfo.ArrayOftCurrency;
import com.soap.ws.client.countryInfo.CountryInfoService;
import com.soap.ws.client.countryInfo.CountryInfoServiceSoapType;
import com.soap.ws.client.countryInfo.TContinent;
import com.soap.ws.client.countryInfo.TCountryCodeAndName;
import com.soap.ws.client.countryInfo.TCurrency;
import com.soap.ws.client.countryInfo.TLanguage;




public class CI_Service {
	
    private List<TContinent> continents;
    private List<TCurrency> currencies;
    private List<TCountryCodeAndName> countries;
    private List<TLanguage> languages;
    
    

	
    public CI_Service() {
        this.continents = this.getContinents();
        this.currencies = this.getCurrencies();
        this.countries = this.getCountries();
        this.languages = this.getLanguages();
    }
    
	public List<TContinent> getContinents(){
		ArrayOftContinent listOfContinents = new ArrayOftContinent();
		CountryInfoService ci_service = new CountryInfoService();
		CountryInfoServiceSoapType CI_serviceSOAP = ci_service.getCountryInfoServiceSoap();
		return CI_serviceSOAP.listOfContinentsByName().getTContinent();
	}
	
	public List<TCurrency> getCurrencies(){
		ArrayOftCurrency listOfCurrencies = new ArrayOftCurrency();
		CountryInfoService ci_service = new CountryInfoService();
		CountryInfoServiceSoapType CI_serviceSOAP = ci_service.getCountryInfoServiceSoap();
		return CI_serviceSOAP.listOfCurrenciesByName().getTCurrency();
	}
	
	public List<TCountryCodeAndName> getCountries(){
		ArrayOftCountryCodeAndName listOfCountries = new ArrayOftCountryCodeAndName();
		CountryInfoService ci_service = new CountryInfoService();
		CountryInfoServiceSoapType CI_serviceSOAP = ci_service.getCountryInfoServiceSoap();
		return CI_serviceSOAP.listOfCountryNamesByName().getTCountryCodeAndName();
	}
	
	public List<TLanguage> getLanguages(){
		ArrayOftCountryCodeAndName listOfCountries = new ArrayOftCountryCodeAndName();
		CountryInfoService ci_service = new CountryInfoService();
		CountryInfoServiceSoapType CI_serviceSOAP = ci_service.getCountryInfoServiceSoap();
		return CI_serviceSOAP.listOfLanguagesByName().getTLanguage();
	}
	
	
	
	
	
}
