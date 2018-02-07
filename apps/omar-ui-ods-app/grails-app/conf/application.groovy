

// Added by the Spring Security Core plugin:
grails.plugin.springsecurity.userLookup.userDomainClassName = 'omar.ui.AuthUser'
grails.plugin.springsecurity.userLookup.authorityJoinClassName = 'omar.ui.AuthUserRole'
grails.plugin.springsecurity.authority.className = 'omar.ui.Role'
grails.plugin.springsecurity.controllerAnnotations.staticRules = [
	[pattern: '/',               access: ['permitAll']],
	[pattern: '/health',         access: ['permitAll']],
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
  [pattern: '/health/**',      filters: 'none'],
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
					github {
						client = org.pac4j.oauth.client.GitHubClient
						defaultRoles = ['ROLE_USER', 'ROLE_GITHUB']
					}
					facebook {
						client = org.pac4j.oauth.client.FacebookClient
						scope = 'email,user_location'
					  fields = 'id,name,first_name,middle_name,last_name,username'
					  defaultRoles = ['ROLE_USER', 'ROLE_FACEBOOK']
					}
				}
			}
		}
	}
}
