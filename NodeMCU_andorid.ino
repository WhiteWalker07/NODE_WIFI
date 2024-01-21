#include <ESP8266WiFi.h>
#include <ArduinoJson.h>

const char* ssid = "UR_SSID";
const char* password = "UR_PSWD";
const int nodeMCU_PORT = 8080;  // Choose a port number same as entered in kotlin files
int i=0;
WiFiServer server(nodeMCU_PORT);

void setup() {
  Serial.begin(115200);
  WiFi.begin(ssid, password); //Connect to WIFI network

  while (WiFi.status() != WL_CONNECTED) {
    delay(1000);
    Serial.println("Connecting to WiFi...");
  }

  Serial.println("Connected to WiFi");
  Serial.print("IP Address: ");
  Serial.println(WiFi.localIP());

  server.begin();
}

void loop() {
  WiFiClient client = server.available();
  Serial.print("23");
  if (client) {
    Serial.println("New client connected");//Connet to client(i.e. Your App)
    //JSON inisalization
    
    while(client.connected()){//Starting infintie loop to send data till the point when client is connected
    
    StaticJsonDocument<500> doc;
    doc["temperature"] = 25.5;//enter filds in JSON file
    doc["humidity"] = i;
    
    // Send JSON data
    //client.println("HTTP/1.1 200 OK");
    //client.println("Content-Type: application/json");
    serializeJson(doc, client);//Sending whole JSON FILE
    //Serial.println(doc)
    client.println("22.45");
    if(i<10)i=100;
    i--;
    }
    Serial.println("Client disconnected");
  }
  

  delay(1000); // Adjust delay based on your requirements
}


