package ru.stqa.pft.rest;


import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.message.BasicNameValuePair;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Set;

import static org.testng.AssertJUnit.assertEquals;

public class RestTests {

    @Test
    public void testCreateIssue() throws IOException {
        Set<Issue> allIssues = getIssues();
        Issue newIssue = new Issue().withSubject("Test issue").withDescription("new test issue");
        int issueID = createIssue(newIssue);
        Set<Issue> newIssues = getIssues();
        allIssues.add(newIssue.withId(issueID));
        assertEquals(newIssues,allIssues);

    }

    private int createIssue(Issue newIssue) throws IOException {
        String json = getExecutor().execute(Request.Post("http://demo.bugify.com/api/issues.json")
        .bodyForm(new BasicNameValuePair("subject", newIssue.getSubject()),
                new BasicNameValuePair("description", newIssue.getDescription())))
                .returnContent().asString();
        JsonElement parsed = new JsonParser().parse(json);
        return parsed.getAsJsonObject().get("issue_id").getAsInt();

    }

    private Set<Issue> getIssues() throws IOException {
        String json = getExecutor().execute(Request.Get("http://demo.bugify.com/api/issues.json"))
                .returnContent().asString();
        //?api_key=556437287bc29921a45599997558b96b
        /*String json = Request.Get("http://demo.bugify.com/api/issues.json")
        .execute().returnContent().asString();*/
        JsonElement parsed = new JsonParser().parse(json);
        JsonElement issues = parsed.getAsJsonObject().get("issues");
        return new Gson().fromJson(issues, new TypeToken<Set<Issue>>(){}.getType());
    }

    private Executor getExecutor(){
        return Executor.newInstance().auth("556437287bc29921a45599997558b96b","");
    }

}
