// @GENERATOR:play-routes-compiler
// @SOURCE:G:/projects/php/TekkenHub/test-back/conf/routes
// @DATE:Wed Mar 03 01:09:28 CST 2021


package router {
  object RoutesPrefix {
    private var _prefix: String = "/"
    def setPrefix(p: String): Unit = {
      _prefix = p
    }
    def prefix: String = _prefix
    val byNamePrefix: Function0[String] = { () => prefix }
  }
}
