Feature: API test for Leads

	Background:
		*def urls= read('config.json')
		* url urls.localUrls.sales
		* header Authorization = 'Bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJqYXlTQGdtYWlsLmNvbSIsImZpcnN0TmFtZSI6ImpheSIsImxhc3ROYW1lIjoic2luaGEiLCJzY29wZSI6WyJvcGVuaWQiXSwidGVuYW50SWQiOiI1ZjQyOGMxYTlhYjQ1NDI1MjlhOTk1Y2QiLCJpc3MiOiJodHRwOi8vbG9jYWxob3N0OjcwMDEvYXV0aC9pc3N1ZXIiLCJ0ZW5hbnRDb2RlIjoidGVuYW50LTEiLCJleHAiOjE1OTk0NzczMzQsInVzZXJJZCI6IjVmNDI4YzFhOWFiNDU0MjUyOWE5OTVjZSIsImF1dGhvcml0aWVzIjpbIntcIl9pZFwiOiB7XCIkb2lkXCI6IFwiNWY0MjhiZTQ3ZGM5OTgzYTNmNWMwOGU1XCJ9LCBcIm5hbWVcIjogXCJURU5BTlRfQURNSU5cIiwgXCJzeXN0ZW1cIjogdHJ1ZSwgXCJjcmVhdGVkRGF0ZVwiOiB7XCIkZGF0ZVwiOiBcIjIwMjAtMDgtMjNUMTU6MzI6NDIuNTY5WlwifSwgXCJsYXN0TW9kaWZpZWREYXRlXCI6IHtcIiRkYXRlXCI6IFwiMjAyMC0wOC0yM1QxNTozMjo0Mi41NjlaXCJ9fSJdLCJqdGkiOiIwNDQxZmIzZi04MGUyLTRhNWItOTUxOC03ZTZmZjU2MTVjOTQiLCJjbGllbnRfaWQiOiJhZG1pbiJ9.XZGNDG4eTspZa2T4CY3hGB3ngAmwjgb8nWuJDijZKVu3UKg7AsW8NDIId9-ts-6d-rGQGtkAMmEF4p_vh-u79YTJ-41UyICorFCu_w7OBtJCehadXQEfLKQJdHXvOAnqVwZXQWJgl6xvu-ETa4qsnnB7DX6UAqD16l-dul5Wg4cuburllogMfunjwTQlCx99P-nSe8vwCtf5NckZHRvkaCoP4zf88-M5VgXQGZ0w_2UI4WEWNrDM0CyceH_Q1GPnz1TQYmpiKpjbAFIaTU4G9P19ZX-kNeX7xHaGA3Xg0ZRJF9neNi3cfO0USg57mYX5jTkvA7ZYD1KmJdc4p31gMA'
	
	Scenario: check get method
		Given path '/leads/q'
		And param rsql = 'deleted==false'
		When method get
		Then status 200
		
	
	
	Scenario: check post method
	* def leadData = read('data.json')
	* def results = read('result.json')
		Given path '/leads'
		And request leadData
		When method post
		Then status 200
		Then print response
		And def capResponse = response
		Then print response.firstName
		Then match capResponse.firstName == "Jay"
		
		
		