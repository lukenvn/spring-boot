package com.luke.example.controller;


import org.jboss.resteasy.client.jaxrs.BasicAuthentication;

import javax.net.ssl.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Enumeration;


@SuppressWarnings("serial")
public class ForwardServlet extends HttpServlet {

    public static final String DESTINATION_HOST = "https://bsaengine.valacr.fil.finnova.com";// no trailing slash
    public static final String USERNAME = "EX1AXON1";
    public static final String PASSWORD = "ex1axon1#";

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) {
        forwardRequest("GET", req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) {
        forwardRequest("POST", req, resp);
    }

    public static void forwardRequest(String method, HttpServletRequest req, HttpServletResponse resp) {

        resp.setContentType("application/json");
        try {
            ignoreSSL();
//            String responseEntity = request(req);
            String destinationUrl = generateDestinationUrl(req);
            forward(method, req, resp, destinationUrl);
//            System.out.println(responseEntity);
//            resp.getWriter().append(responseEntity).close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
//    public static String request(HttpServletRequest req) throws Exception {
//
//        System.out.println(destinationUrl);
//
//
//        Client client =  IgnoreSSLClient();
//        Response response = client.target(destinationUrl)
//                .register(new BasicAuthentication(USERNAME,PASSWORD))
//                .request(MediaType.APPLICATION_JSON).get();
//        return response.readEntity(String.class);
//    }

    public static Client IgnoreSSLClient() throws Exception {
        SSLContext sslcontext = SSLContext.getInstance("TLS");
        sslcontext.init(null, new TrustManager[]{new X509TrustManager() {
            public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {}
            public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {}
            public X509Certificate[] getAcceptedIssuers() { return new X509Certificate[0]; }

        }}, new java.security.SecureRandom());
        return ClientBuilder.newBuilder().sslContext(sslcontext).hostnameVerifier((s1, s2) -> true).build();
    }
    private static String generateDestinationUrl(HttpServletRequest req) {
        String requestUrl = req.getRequestURI().replace("/m", "");
        String queryString = req.getQueryString() != null ? "?" + req.getQueryString() : "";
        return DESTINATION_HOST + requestUrl + queryString;
    }

    private static void forward(String method, HttpServletRequest req, HttpServletResponse resp, String destinationUrl) throws IOException {
        final URL url = new URL(destinationUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod(method);

        final Enumeration<String> headers = req.getHeaderNames();
        while (headers.hasMoreElements()) {
            final String header = headers.nextElement();
            final Enumeration<String> values = req.getHeaders(header);
            while (values.hasMoreElements()) {
                final String value = values.nextElement();
                conn.addRequestProperty(header, value);
            }
        }
        String basicAuth = generateBasicAuth();
//            String basicAuth = "Basic " + new String(new Base64().encode(userpass.getBytes()));
        conn.setRequestProperty("Authorization", basicAuth);
        conn.setRequestProperty("Content-Type", "application/json");

        //conn.setFollowRedirects(false);  // throws AccessDenied exception
        conn.setUseCaches(false);
        conn.setDoInput(true);
        final boolean hasoutbody = (method.equals("POST"));
        conn.setDoOutput(hasoutbody);
        conn.connect();

        final byte[] buffer = new byte[16384];
        while (hasoutbody) {
            final int read = req.getInputStream().read(buffer);
            if (read <= 0) break;
            conn.getOutputStream().write(buffer, 0, read);
        }

        resp.setContentType(conn.getContentType());
        resp.setStatus(conn.getResponseCode());
        for (int i = 0; ; ++i) {
            final String header = conn.getHeaderFieldKey(i);
            if (header == null) break;
            final String value = conn.getHeaderField(i);
            resp.setHeader(header, value);
        }

        while (true) {
            final int read = conn.getInputStream().read(buffer);
            if (read <= 0) break;
            resp.getOutputStream().write(buffer, 0, read);
        }
    }

    private static String generateBasicAuth() {
        String userpass = USERNAME + ":" + PASSWORD;
        return "Basic " + javax.xml.bind.DatatypeConverter.printBase64Binary(userpass.getBytes());
    }


    public static void ignoreSSL() throws NoSuchAlgorithmException, KeyManagementException {
        // Create a trust manager that does not validate certificate chains
        TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }

            public void checkClientTrusted(X509Certificate[] certs, String authType) {
            }

            public void checkServerTrusted(X509Certificate[] certs, String authType) {
            }
        }
        };

        // Install the all-trusting trust manager
        SSLContext sc = SSLContext.getInstance("SSL");
        sc.init(null, trustAllCerts, new java.security.SecureRandom());
        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

        // Create all-trusting host name verifier
        HostnameVerifier allHostsValid = new HostnameVerifier() {
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        };

        // Install the all-trusting host verifier
        HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);

        HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
    }

}