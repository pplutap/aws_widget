import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.lambda.AWSLambda;
import com.amazonaws.services.lambda.AWSLambdaClientBuilder;
import com.amazonaws.services.lambda.model.InvokeRequest;
import com.amazonaws.services.lambda.model.InvokeResult;


public class WidgetLambdaClient {

    public static void main(String[] args) {
        BasicAWSCredentials credentials =
                new BasicAWSCredentials("accessKey", "secretKey");
        AWSLambda lambda = AWSLambdaClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials)).withRegion(Regions.US_EAST_2)
                .build();
        InvokeRequest request = new InvokeRequest()
                .withFunctionName("get-widget")
                .withPayload("{ \"id\" : \"1\" }");
        try {
            InvokeResult result = lambda.invoke(request);
            System.out.println("Status code: " + result.getStatusCode());
            String json = new String(result.getPayload().array(), "UTF-8");
            System.out.println(json);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
