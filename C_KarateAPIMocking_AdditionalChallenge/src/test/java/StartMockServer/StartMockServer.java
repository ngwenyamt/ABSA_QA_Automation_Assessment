package StartMockServer;

import com.intuit.karate.core.MockServer;

public class StartMockServer {
    public static void main(String[] args) {
        // Create a builder for the mock server using the specified feature file
        MockServer.Builder builder = MockServer.feature("classpath:Mocks/Mocks.feature");

        // Build and start the mock server on port 8080
        MockServer server = builder.http(8080).build();

        // Print the port number where the mock server is running
        System.out.println("Mock server started on port: " + server.getPort());
    }
}