#define _LDR 17
#define _LDR2 16
#define _LED_YELLOW 33

void IRAM_ATTR turnLEDOn_ISR(){
  digitalWrite(_LED_YELLOW, HIGH);
}

void IRAM_ATTR turnLEDOff_ISR(){
  digitalWrite(_LED_YELLOW, LOW);
}

void setup() {
  // put your setup code here, to run once:
  Serial.begin(9600);
  
  pinMode(_LED_YELLOW, OUTPUT);
  //LDR
  pinMode(_LDR, INPUT);
  pinMode(_LDR2, INPUT);

  digitalWrite(_LED_YELLOW, HIGH);
  
  attachInterrupt(_LDR, turnLEDOn_ISR, RISING);
  attachInterrupt(_LDR2, turnLEDOff_ISR, FALLING);
}

void loop() {
  /*
  int leitura = analogRead(_LDR);
  // Realizar o print da leitura no serial
  Serial.println("Leitura do Sensor de LDR:");
  Serial.println(leitura);
  // realizar um delay e inicializar leitura daqui a 2 segundos
  delay(2000);
  */
}
