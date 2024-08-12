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

#### Endpoints

##### Products

1. **Create Product**
   - **Endpoint:** `POST /products`
   - **Request Body Template:**
     ```json
     {
       "name": "string",
       "description": "string",
       "price": "number",
       "categoryId": "string"
     }
     ```


2. **Get Product by ID**
   - **Endpoint:** `GET /products/{id}`
   - **Request Body:** None


3. **Update Product**
   - **Endpoint:** `PUT /products/{id}`
   - **Request Body Template:**
     ```json
     {
       "name": "string",
       "description": "string",
       "price": "number",
       "categoryId": "string"
     }
     ```


4. **Delete Product**
   - **Endpoint:** `DELETE /products/{id}`
   - **Request Body:** None


##### Categories

1. **Create Category**
   - **Endpoint:** `POST /categories`
   - **Request Body Template:**
     ```json
     {
       "name": "string",
       "description": "string"
     }
     ```


2. **Get Category by ID**
   - **Endpoint:** `GET /categories/{id}`
   - **Request Body:** None


3. **Update Category**
   - **Endpoint:** `PUT /categories/{id}`
   - **Request Body Template:**
     ```json
     {
       "name": "string",
       "description": "string"
     }
     ```


4. **Delete Category**
   - **Endpoint:** `DELETE /categories/{id}`
   - **Request Body:** None

  
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
