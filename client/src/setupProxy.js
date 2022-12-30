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
        "http://ec2-52-78-85-133.ap-northeast-2.compute.amazonaws.com:8080/",
      pathRewrite: { "^/api2/": "/" },
      changeOrigin: true,
    })
  );
};
