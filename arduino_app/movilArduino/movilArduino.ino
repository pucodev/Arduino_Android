// Motor1
int EN_M1 = 53;
int IN1_M1 = 13;
int IN2_M1 = 12;

// Motor2
int EN_M2 = 51;
int IN1_M2 = 11;
int IN2_M2 = 10;

void setup() {
  // Iniciamos comunicación
  Serial.begin(9600);

  // Seteamos input y outputs
  pinMode(EN_M1, OUTPUT);
  pinMode(IN1_M1, OUTPUT);
  pinMode(IN1_M1, OUTPUT);
  pinMode(EN_M2, OUTPUT);
  pinMode(IN1_M2, OUTPUT);
  pinMode(IN2_M2, OUTPUT);
}

void loop() {
  byte value;
  
  // leemos valor del puerto serie
  value = Serial.read();
  
  // Analizamos value
  switch(value){
    case 49:
      avanza();
      break;
    case 50:
      retrocede();
      break;
    case 51:
      derecha();
      break;
    case 52:
      izquierda();
      break;
    case 53:
      stop();
      break;
      
      
    default:
      break;
  }

}

void avanza() {
  digitalWrite(EN_M1, HIGH); 
  digitalWrite(IN1_M1, LOW); 
  digitalWrite(IN2_M1, HIGH); 

  digitalWrite(EN_M2, HIGH); 
  digitalWrite(IN1_M2, LOW); 
  digitalWrite(IN2_M2, HIGH); 
}

void retrocede() {
  digitalWrite(EN_M1, HIGH); 
  digitalWrite(IN1_M1, HIGH); 
  digitalWrite(IN2_M1, LOW); 

  digitalWrite(EN_M2, HIGH); 
  digitalWrite(IN1_M2, HIGH); 
  digitalWrite(IN2_M2, LOW); 
}

void derecha() {
  digitalWrite(EN_M1, HIGH); 
  digitalWrite(IN1_M1, LOW); 
  digitalWrite(IN2_M1, HIGH); 

  digitalWrite(EN_M2, HIGH); 
  digitalWrite(IN1_M2, HIGH); 
  digitalWrite(IN2_M2, LOW); 
}

void izquierda() {
  digitalWrite(EN_M1, HIGH); 
  digitalWrite(IN1_M1, HIGH); 
  digitalWrite(IN2_M1, LOW); 

  digitalWrite(EN_M2, HIGH); 
  digitalWrite(IN1_M2, LOW); 
  digitalWrite(IN2_M2, HIGH); 
}

void stop() {
  digitalWrite(EN_M1, LOW);
  digitalWrite(EN_M2, LOW); 
}



