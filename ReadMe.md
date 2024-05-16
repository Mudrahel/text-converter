# Introduction
This small project represents completion of the following take home test task for One Ink company.

#The task:
*   [RPA Dev] Long-running job Web App:
*   General idea: simulate a hard processing work of the input data items given by user.
*   Bare minimum requirements:
    
    Provide us with your example of a Java app on the backend, and web UI (HTML/JS, possibly Angular/React etc.) on the front-end. On the front-end side user should be able to enter the text into the text field, press "Convert" button, and get this text encoded into the base64 format.
        Encoding should be performed on the backend side. Encoded string should be returned to the client one character at time, one by one, and for each returned character there should be random pause on the server 1-5 seconds. All received characters should form a string in a UI textbox, hence it will be updated in real-time by adding new incoming characters. User cannot start another encoding process on this page while the current one is in progress, but user can press "cancel" button and thus cancel the currently running process.

*   Bonus (recommended) requirements:
    *   Use Spring as the web app framework
    *   Implement business logic as layers (services), with unit tests for each respectively
        
    
Example of the app functioning:

     input = "Hello, World!". Generated base64="SGVsbG8sIFdvcmxkIQ=="
*   What web client receives from the server:
    *   Random pause… "S"
    *   Random pause… "G"
    *   Random pause… "V"
    *   Random pause… "s"
    *   Etc.
*   What does user see in the result text field on web UI:
    *   Random pause… "S"
    *   Random pause… "SG"
    *   Random pause… "SGV"
    *   Random pause… "SGVs"
    *   Etc.


# Run details

#### Web app configured to run on: localhost:8585

