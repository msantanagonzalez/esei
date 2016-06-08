#line 1 "C:/Users/tatiux/Desktop/ESEI/Curso2015-2016/HAE/Proyecto/Code/hae2.c"
unsigned char distancia[14];
const unsigned long baund_rate = 9600;
sbit LCD_RS at RC0_bit;
sbit LCD_EN at RC1_bit;
sbit LCD_D7 at RB7_bit;
sbit LCD_D6 at RB6_bit;
sbit LCD_D5 at RB5_bit;
sbit LCD_D4 at RB4_bit;

sbit LCD_RS_Direction at TRISC0_bit;
sbit LCD_EN_Direction at TRISC1_bit;
sbit LCD_D7_Direction at TRISB7_bit;
sbit LCD_D6_Direction at TRISB6_bit;
sbit LCD_D5_Direction at TRISB5_bit;
sbit LCD_D4_Direction at TRISB4_bit;

void interrupt()
{
 if ((PIR1.RCIF == 1) && (UART1_Data_Ready() == 1))
 {
 if (UART1_Data_Ready() == 1) {
 UART1_Read_Text(distancia, "EOL", 255);
 Lcd_out(1,1, distancia);
 }
 }
 PIR1.RCIF = 0;
}

void main(){
Lcd_Init();
UART1_Init(baund_rate);


RCSTA.SREN = 1;
RCON.IPEN = 0;
PIR1.RCIF = 0;
PIE1.RCIE = 1;
INTCON.PEIE = 1;
INTCON.GIE = 1;

while(1);
}
