import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class GetWidgetHandler implements RequestHandler<WidgetRequest, Widget> {
    @Override
    public Widget handleRequest(WidgetRequest input, Context context) {
        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.defaultClient();

        DynamoDBMapper mapper = new DynamoDBMapper(client);

        Widget widget = mapper.load(Widget.class, input.getId());
        if (widget == null) {
            context.getLogger().log("No widget found with ID: " + input.getId());
            return new Widget();
        }
        return widget;
    }
}
