package org.menosprezo.systembarber.service;

import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.entity.UrlEncodedFormEntity;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.message.BasicNameValuePair;
import org.apache.hc.core5.net.URIBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Service
public class MailgunEmailService {

    @Value("${mailgun.api.base.url}")
    private String mailgunApiBaseUrl;

    @Value("${mailgun.api.key}")
    private String mailgunApiKey;

    @Value("${mailgun.domain}")
    private String mailgunDomain;

    @Value("${mailgun.from.email}")
    private String mailgunFromEmail;

    public void enviarEmail(String to, String subject, String text) {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            URI uri = new URIBuilder(mailgunApiBaseUrl + "/" + mailgunDomain + "/messages").build();
            HttpPost httpPost = new HttpPost(uri);

            httpPost.setHeader("Authorization", "Basic " +
                    java.util.Base64.getEncoder().encodeToString(("api:" + mailgunApiKey).getBytes()));

            List<BasicNameValuePair> params = new ArrayList<>();
            params.add(new BasicNameValuePair("from", mailgunFromEmail));
            params.add(new BasicNameValuePair("to", to));
            params.add(new BasicNameValuePair("subject", subject));
            params.add(new BasicNameValuePair("text", text));
            httpPost.setEntity(new UrlEncodedFormEntity(params));

            try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
                System.out.println("Response status: " + response.getCode());
                System.out.println("Response body: " + EntityUtils.toString(response.getEntity()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
