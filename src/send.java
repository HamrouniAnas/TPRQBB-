import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class send {
    public String message;
    public String queueName;

    send(String message, String queueName) {
        this.message = message;
        this.queueName = queueName;
    }

    public void Send() throws Exception {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");

        try (Connection connection = connectionFactory.newConnection()) {
            Channel channel = connection.createChannel();
            channel.queueDeclare(this.queueName, false, false, false, null);

            channel.basicPublish("", this.queueName, null, this.message.getBytes());
            System.out.println(" [x] sent '" + message + " '");
        }

    }
}