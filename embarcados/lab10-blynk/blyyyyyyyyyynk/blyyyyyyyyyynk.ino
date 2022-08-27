#define BLYNK_TEMPLATE_ID "TMPL4KruUJAK"
#define BLYNK_DEVICE_NAME "DiogosEsp"
#define BLYNK_AUTH_TOKEN "8khRzJUPqsxYnqzVM7VOHMS1Pm0vzMHt"
#define BLYNK_PRINT Serial

#include "WiFi.h"
#include "WiFiClient.h"
#include "BlynkSimpleEsp32.h"
#include "DHT.h"

#define LED_PIN 23
#define DHT_PIN 22
#define _LRD 32
#define DHTTYPE DHT11
DHT dht(DHT_PIN, DHTTYPE);

BlynkTimer timer;

char auth[] = BLYNK_AUTH_TOKEN;
char ssid[] = "ICOMP_1A";
char pass[] = "1comp@14#";

BLYNK_WRITE (V2) {
  int value = param.asInt();
  digitalWrite(LED_PIN, value);
}

void enviaUmidade() {
  int humidity = dht.readHumidity();
  Blynk.virtualWrite(V0, humidity);
}

void enviaTemperatura() {
  int temperature = dht.readTemperature();
  Blynk.virtualWrite(V1, temperature);
}

void enviaLDR() {
  int ldrValor = analogRead(_LRD);
  Blynk.virtualWrite(V3, ldrValor);
}

void setup() {
  pinMode(LED_PIN, OUTPUT);
  pinMode(DHT_PIN, INPUT);
  
  dht.begin();

  Serial.begin(115200);
  Blynk.begin(auth, ssid, pass);

  timer.setInterval(1000L, enviaUmidade);
  timer.setInterval(1000L, enviaTemperatura);
  timer.setInterval(1000L, enviaLDR);
}

void loop() {
  Blynk.run();
  timer.run();
}
