import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import javax.swing.*;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class ChatApp2 {
    public final static String QUEUE_NAME = "user2";
    private static JTextField textField1;

    public ChatApp2() throws IOException, TimeoutException {
        JFrame f = new JFrame("User Two");
        JTextArea area = new JTextArea("Enter your message:");
        area.setBounds(10, 30, 200, 100);
        JButton sendButton = new JButton("Send");
        this.textField1 = new JTextField();
        sendButton.setBounds(220, 30, 100, 30);
        sendButton.addActionListener(e -> {
                    send sender = new send(area.getText(), QUEUE_NAME);
                    try {
                        sender.Send();

                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }
        );
        JLabel userLabel2 = new JLabel("User one say: ");
        userLabel2.setBounds(50, 100, 300, 30);
        f.add(userLabel2);
        f.add(area);
        f.add(sendButton);
        f.add(textField1);
        f.setSize(500, 500);
        f.setLayout(null);
        f.setVisible(true);

        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare("user1", false, false, false, null);
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String receivedMessage = new String(delivery.getBody(), "UTF-8");
            userLabel2.setText("User one say: " + receivedMessage);
            System.out.println(" [x] sent '" + receivedMessage + " '");

        };
        channel.basicConsume("user1", true, deliverCallback, consumerTag -> {
        });
    }

    public static void main(String[] args) throws Exception {
        new ChatApp2();
    }

}
