
# Chat Application

A real-time chat application built in Java using Swing for the graphical user interface, Socket Programming for communication, and Multithreading to handle multiple clients simultaneously. This application consists of a separate server and client application, where the server must be running before clients can connect.

## Features
- Real-time messaging between clients
- Support for multiple clients via multithreading
- User-friendly interface using Swing
- Private messaging capabilities

## Technologies Used
- Java
- Swing (for GUI)
- Socket Programming
- Multithreading

## Getting Started

### Prerequisites
- Java Development Kit (JDK) 8 or higher

### Installation
1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/ChatApplication.git
   ```
2. Navigate to the project directory:
   ```bash
   cd ChatApplication
   ```

### Running the Application
1. **Start the Server**: 
   - Navigate to the server directory and run the Server application:
   ```bash
   javac Server.java
   java Server
   ```
   
2. **Start the Client**:
   - Open a new terminal window, navigate to the client directory, and run the Client application:
   ```bash
   javac Client.java
   java Client
   ```

3. **Connect**:
   - Enter the server IP address and the desired username to start chatting.

## Usage
- Once the server is running, multiple clients can connect.
- Users can send and receive messages in real-time.


