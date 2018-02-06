import omar.ui.AuthUserPasswordEncoderListener
// Place your Spring DSL code here
import omar.ui.OmarSitesConfig

beans = {
    authUserPasswordEncoderListener(AuthUserPasswordEncoderListener)
  omarSitesConfig(OmarSitesConfig)
  omarConfigConverter(OmarSitesConfig.OmarConfigConverter)
}
