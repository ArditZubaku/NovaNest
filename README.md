# NovaNest Bank

## Overview

NovaNest Bank is a JavaFX application built with Maven for managing banking operations. It offers a range of features for both bank admins and clients, including account management, transactions, and administrative controls.

## Project Structure

The project is organized into several packages and resources:

- `com.zubaku.novanest`: The root package.
    - `controllers`: Contains controller classes for handling user interactions.
        - `admin`: Controllers for admin functionalities.
        - `client`: Controllers for client-side operations.
    - `models`: Contains model classes that represent entities such as `Account`, `Client`, and `Transaction`.
    - `processors`: Houses classes responsible for processing data, like factories and view processors.
    - `repository`: Includes classes for data access and manipulation.
    - `utils`: Provides utility classes and enums for the application.
- `resources`: Contains non-source code resources.
    - `images`: Graphics and icons used in the application.
    - `styles`: CSS files for styling the application's user interface.
    - `views`: FXML files defining the user interface layouts.
        - `admin`: FXML files for admin-related views.
        - `client`: FXML files for client-related views.
- `App.java`: The main entry point for the application.

## Prerequisites

- Java JDK 17 (personally used version 17.0.21)
- Maven 3.6.3 or later

## Setup and Installation

1. Clone the repository to your local machine.
2. Navigate to the project directory.
3. Run `mvn clean install` to build the project and install the dependencies.
4. To start the application, run `mvn javafx:run`.

## Contributing

Contributions to NovaNest Bank are welcome. Please follow these steps:

1. Fork the repository.
2. Create a new branch for your feature (`git checkout -b feature/YourFeature`).
3. Make changes and commit (`git commit -am 'Add some feature'`).
4. Push to the branch (`git push origin feature/YourFeature`).
5. Open a pull request.

## License

This project is licensed under the MIT License - see the LICENSE file for details.
