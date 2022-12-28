const { createProxyMiddleware } = require("http-proxy-middleware");

module.exports = function (app) {
  // 서버 주소: "http://3.36.232.23/"
  // json-server 주소: "http://localhost:3001"
  app.use(
    "^/api/",
    createProxyMiddleware({
      target: "http://localhost:3001",
      pathRewrite: { "^/api/": "/" },
      changeOrigin: true,
    })
  );
};
