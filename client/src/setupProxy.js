const { createProxyMiddleware } = require("http-proxy-middleware");

module.exports = function (app) {
  app.use(
    "^/api/",
    createProxyMiddleware({
      target: "http://3.36.232.23:8080/",
      pathRewrite: { "^/api/": "/" },
      changeOrigin: true,
    })
  );
  app.use(
    "^/api2/",
    createProxyMiddleware({
      target:
        "http://ec2-3-38-213-53.ap-northeast-2.compute.amazonaws.com:8080/",
      pathRewrite: { "^/api2/": "/" },
      changeOrigin: true,
    })
  );
};
