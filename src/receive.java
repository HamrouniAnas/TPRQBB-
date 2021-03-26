import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

public class receive {
/*
    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(queueName, false, false, false, null);
        System.out.println("[y] Waiting for messages, to exit press CTRL+C");
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String receivedMessage = new String(delivery.getBody(), "UTF-8");
            System.out.println("[x] sent ' " + receivedMessage + " '");
        };
        channel.basicConsume(queueName, true, deliverCallback, consumerTag -> {
        });
    }*/
}