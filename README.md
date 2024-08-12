# Desafio AnotaAi

## Overview

This project is a Spring Boot application that manages categories and products, integrating with AWS services such as S3 and SNS. It includes functionalities for adding, updating, and deleting categories and products, and it handles messages from AWS SQS.

## Technologies Used

- Java
- Spring Boot
- Maven
- AWS SDK (S3, SNS)
- JUnit 5
- Mockito
- IntelliJ IDEA

## Project Structure

- `src/main/java`: Contains the main application code.
- `src/test/java`: Contains the test code.
- `src/test/resources`: Contains test resources like `application-test.properties`.
- `ExternalFiles/index.mjs`: Contains the JavaScript code for handling AWS S3 operations.

## Setup

### Prerequisites

- Java 11 or higher
- Maven
- AWS account with S3 and SNS configured
- IntelliJ IDEA (recommended)

### Configuration

1. **AWS Credentials**: Set your AWS credentials in the `src/test/resources/application-test.properties` file.
    ```ini
    aws.accessKeyId=${AWS_KEY_ID}
    aws.secretKey=${AWS_SECRET}
    aws.region=us-east-1
    aws.sns.topic.catalog.arn=TOPICO_FAKE
    server.port=8070
    ```

2. **Environment Variables**: Ensure that the environment variables `AWS_KEY_ID` and `AWS_SECRET` are set with your AWS credentials.

### Running the Application

1. **Build the Project**: Run the following command to build the project using Maven.
    ```sh
    mvn clean install
    ```

2. **Run the Application**: You can run the application from IntelliJ IDEA or using the following Maven command.
    ```sh
    mvn spring-boot:run
    ```

### Running Tests

To run the tests, use the following Maven command:
```sh
mvn test
```

## Usage

### Category Management

- **Insert Category**: Adds a new category.
- **Update Category**: Updates an existing category.
- **Delete Category**: Deletes an existing category.
- **Get All Categories**: Retrieves all categories.
- **Get Category by ID**: Retrieves a category by its ID.

### AWS Integration

- **S3 Operations**: Handles S3 operations such as getting and putting objects.
- **SNS Operations**: Publishes messages to SNS topics.

## Contributing

1. Fork the repository.
2. Create a new branch (`git checkout -b feature-branch`).
3. Commit your changes (`git commit -am 'Add new feature'`).
4. Push to the branch (`git push origin feature-branch`).
5. Create a new Pull Request.

## License

This project is licensed under the MIT License.
```
