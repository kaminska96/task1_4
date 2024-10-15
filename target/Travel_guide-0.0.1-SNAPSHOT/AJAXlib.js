const xhr = new XMLHttpRequest();
xhr.open('GET', 'CountryInfo_servlet', true);
xhr.onreadystatechange = function() {
    if (xhr.readyState === 4) {
        if (xhr.status === 200) {
            try {
                const data = JSON.parse(xhr.responseText);
				console.log(data); 
				const continents = data.continents;
				const countries = data.countries;
				const currencies = data.currencies;
				const languages = data.languages;
                
                const ul_continents = document.getElementById('continents');
				const ul_countries = document.getElementById('countries');
				const ul_languages = document.getElementById('languages');
				const ul_currencies = document.getElementById('currencies');


                continents.forEach(continent => {
                    const li = document.createElement('li');
                    li.classList.add('list-group-item'); 
                    li.textContent = continent.sName; 

                    ul_continents.appendChild(li);
                });
				
				countries.forEach(country => {
				    const li = document.createElement('li');
				    li.classList.add('list-group-item'); 
                    li.textContent = `${country.sisoCode} - ${country.sName}`;		
					
					li.addEventListener('click', function() {
			            getCountryInfo(country.sisoCode);
				    });		    
					ul_countries.appendChild(li);
				});
				
				currencies.forEach(language => {
				    const li = document.createElement('li');
				    li.classList.add('list-group-item'); 
				    li.textContent = language.sName; 
				    ul_languages.appendChild(li);
				});
				
				languages.forEach(currency => {
				    const li = document.createElement('li');
				    li.classList.add('list-group-item'); 
				    li.textContent = currency.sName; 
				    ul_currencies.appendChild(li);
				});
				
            } catch (e) {
                console.error('Data processing error', e);
            }
        } else {
            console.error('Continent loading error:', xhr.statusText);
        }
    }
};
xhr.send();




    function getCountryInfo(countryISOCode) {
        const xhr = new XMLHttpRequest();
        xhr.open('GET', `CountryInfo_servlet?isoCode=${countryISOCode}`, true);
        xhr.onreadystatechange = function() {
            if (xhr.readyState === 4 && xhr.status === 200) {
                const countryInfo = JSON.parse(xhr.responseText);
                console.log(countryInfo); 

                document.getElementById('countryInfo').innerHTML = `
                    <h3>${countryInfo.name}</h3>
                    <p><strong>Capital city:</strong> ${countryInfo.capital}</p>
                    <p><strong>Currency:</strong> ${countryInfo.currencyName} (${countryInfo.currencyCode})</p>
                    <p><strong>Phone code:</strong> ${countryInfo.phoneCode}</p>
                    <img src="${countryInfo.flag}" alt="Flag ${countryInfo.name}" />
                `;
            }
        };
        xhr.send();
    }
	
	
	
	function getCountryByISOCode(countryISOCode) {
			
	        const xhr = new XMLHttpRequest();
	        xhr.open('GET', `CountryInfo_servlet?isoCode=${countryISOCode}`, true);
	        xhr.onreadystatechange = function() {
	            if (xhr.readyState === 4 && xhr.status === 200) {
	                const countryInfo = JSON.parse(xhr.responseText);
	                
	                if (countryInfo && countryInfo.name) {
	                    displayCountryInfo(countryInfo);
	                } else {
	                    document.getElementById('countryInfo').innerHTML = "<p>The country you entered was not found.</p>";
	                }
	            }
	        };
	        xhr.send();
	    }

    function getCountryByName(countryName) {
	        const xhr = new XMLHttpRequest();
	        xhr.open('GET', `CountryInfo_servlet?name=${countryName}`, true);
	        xhr.onreadystatechange = function() {
	            if (xhr.readyState === 4 && xhr.status === 200) {
	                const isoCode = xhr.responseText;

	                if (isoCode && isoCode !== "null") {
	                    getCountryByISOCode(isoCode);
	                } else {
	                    document.getElementById('countryInfo').innerHTML = "<p>The country you entered was not found.</p>";
	                }
	            }
	        };
	        xhr.send();
	    }
		

	function displayCountryInfo(countryInfo) {
		document.getElementById('countryInfo').innerHTML = `
		    <h3>${countryInfo.name}</h3>
		    <p><strong>Capital city:</strong> ${countryInfo.capital}</p>
		    <p><strong>Currency:</strong> ${countryInfo.currencyName} (${countryInfo.currencyCode})</p>
		    <p><strong>Phone code:</strong> ${countryInfo.phoneCode}</p>
		    <img src="${countryInfo.flag}" alt="Flag ${countryInfo.name}" />
		`;
       }

	   
	   function sendHello(name) {
	       const xhr = new XMLHttpRequest();
	       xhr.open('GET', `Hello_servlet?name=${encodeURIComponent(name)}`, true); 
	       xhr.onreadystatechange = function() {
	           if (xhr.readyState === 4 && xhr.status === 200) {
	               
	               document.getElementById('greetingResult').innerHTML = xhr.responseText;
	           }
	       };
	       xhr.send(); 
	   }
	   

