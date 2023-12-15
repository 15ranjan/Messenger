# **Simple Messaging Application**

## Build Steps

 1. run the run.sh file in the project from the project directory. It should create the required image and run the container.

                            OR
 2. Go to the project root directory. Copy the three commands from the run.sh file and paste it in your terminal one by one. First is to create the jar, second command is to build the image and second to run the container.
 3. API endpoints can be imported to POSTMAN to test using the import feature. CLick on import and upload the file which contains the collection. It is named `Messenger.postman_collection.json`. To test the APIs update the fields with proper values or the values you want and send the request.
 

API EndPoints start with `/api/v1`

Use apiKey for secure endpoints. We get apiKey as response of `/login`. This can be passed in the header. Get all users requires username and apiKey in the header.

Server can be accessed at `localhost:6969`

**Test API** - [localhost:6969/api/v1/hello/prince]()

Assumptions : 
1. If a message is delivered it is assumed to be read, because we will want undelivered message, not the unread ones.
2. Messages have extra fields than asked as we will need them on client side to arrange, order, show required messages.
3. I have made the application to be in-memory as of now, database support can be added and repositories can be modified accordingly.
4. I am generating the API key as `username + "123"`, which can be later improved if needed

Missing Feature :
1. Have not implemented any APIs for group chat, though I have defined the model for group. Implementation of group chat will be similar to peer to peer chat. 
2. To make it a full fledged application we can add UI, websockets support and persisitent storage to send messages in real time rather than client asking repeatedly for unread messages.
