

// Added by the Spring Security Core plugin:
grails.plugin.springsecurity.userLookup.userDomainClassName = 'omar.ui.AuthUser'
grails.plugin.springsecurity.userLookup.authorityJoinClassName = 'omar.ui.AuthUserRole'
grails.plugin.springsecurity.authority.className = 'omar.ui.Role'
grails.plugin.springsecurity.controllerAnnotations.staticRules = [
	[pattern: '/',               access: ['permitAll']],
	[pattern: '/error',          access: ['permitAll']],
	[pattern: '/index',          access: ['permitAll']],
	[pattern: '/index.gsp',      access: ['permitAll']],
	[pattern: '/shutdown',       access: ['permitAll']],
	[pattern: '/assets/**',      access: ['permitAll']],
	[pattern: '/**/js/**',       access: ['permitAll']],
	[pattern: '/**/css/**',      access: ['permitAll']],
	[pattern: '/**/images/**',   access: ['permitAll']],
	[pattern: '/**/favicon.ico', access: ['permitAll']],
	[pattern: '/views/**', access: ['permitAll']]
]

grails.plugin.springsecurity.filterChain.chainMap = [
	[pattern: '/assets/**',      filters: 'none'],
	[pattern: '/**/js/**',       filters: 'none'],
	[pattern: '/**/css/**',      filters: 'none'],
	[pattern: '/**/images/**',   filters: 'none'],
	[pattern: '/**/favicon.ico', filters: 'none'],
	[pattern: '/views/**',      filters: 'none'],

	//Stateless chain
	[
			pattern: '/api/**',
			filters: 'JOINED_FILTERS,-anonymousAuthenticationFilter,-exceptionTranslationFilter,-authenticationProcessingFilter,-securityContextPersistenceFilter,-rememberMeAuthenticationFilter'
	],

	//Traditional chain
	[
			pattern: '/**',
			filters: 'JOINED_FILTERS,-restTokenValidationFilter,-restExceptionTranslationFilter'
	]
]

grails {
	plugin {
		springsecurity {
			rest {
				oauth {
// 					// frontendCallbackUrl = { String tokenValue ->
// 					// 	"http://172.16.0.119:8080/omar-ui-ods/omar?token=${tokenValue}"
// 					// }
// 					frontendCallbackUrl = "http://172.16.0.119:8080/omar-ui-ods/omar?token="
					github {
						client = org.pac4j.oauth.client.GitHubClient
// 						key = '09a9d3c110ecd0834841'
// 						secret = 'f466b346e3ca43fe716ed6dcd80e3fd4a09f2190'
						defaultRoles = ['ROLE_USER']
					}
					facebook {
						client = org.pac4j.oauth.client.FacebookClient
						defaultRoles = ['ROLE_USER']
					}
				}
			}
		}
	}
}