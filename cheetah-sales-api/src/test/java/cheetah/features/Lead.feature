Feature: API test for Leads

	Background:
		* def urls = read('../../Config/config.json')
		* url urls.localUrls.sales
		* def authKey = read('../../TestData/authkey.txt')
		* header Authorization = 'Bearer '+ authKey
	
	
	
	Scenario: check post method
	* def leadData = read('../../TestData/data.json')
	* def results = read('../../TestData/result.json')
		Given path '/leads'
		And request leadData
		When method post
		Then status 200
		Then print response
		And match response.firstName == "Jay"
	
	
	
	Scenario: check get method
		Given path '/leads/q'
		And param rsql = 'deleted==false'
		When method get
		Then status 200