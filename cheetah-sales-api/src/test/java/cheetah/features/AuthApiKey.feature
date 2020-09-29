Feature: API test for Leads

	Background:
		* def urls = read('../../Config/config.json')
		* url urls.localUrls.auth
		* header Authorization = 'Basic YWRtaW46bmltZGEzMjEh'
	
	
	Scenario: check get method
	
		Given path '/auth/oauth/token'
		And param grant_type = 'password'
		And param scope = 'openid'
		And param username = 'jayS@gmail.com'
		And param password = 'test12345'
		And request ''
		When method post
		Then status 200
		And print response
		And def capResponse = response
		And print capResponse.access_token
		* def authkey = Java.type('cheetah.integration.library.WriteFile')
		* def write = authkey.writeTextFile(capResponse.access_token)
		Then print write