
_interrupt:

;hae2.c,17 :: 		void interrupt() //rutina de servicio de interrupciones (MikroC)
;hae2.c,19 :: 		if ((PIR1.RCIF == 1) && (UART1_Data_Ready() == 1))
	BTFSS       PIR1+0, 5 
	GOTO        L_interrupt2
	CALL        _UART1_Data_Ready+0, 0
	MOVF        R0, 0 
	XORLW       1
	BTFSS       STATUS+0, 2 
	GOTO        L_interrupt2
L__interrupt6:
;hae2.c,21 :: 		if (UART1_Data_Ready() == 1) {
	CALL        _UART1_Data_Ready+0, 0
	MOVF        R0, 0 
	XORLW       1
	BTFSS       STATUS+0, 2 
	GOTO        L_interrupt3
;hae2.c,22 :: 		UART1_Read_Text(distancia, "EOL", 255);
	MOVLW       _distancia+0
	MOVWF       FARG_UART1_Read_Text_Output+0 
	MOVLW       hi_addr(_distancia+0)
	MOVWF       FARG_UART1_Read_Text_Output+1 
	MOVLW       ?lstr1_hae2+0
	MOVWF       FARG_UART1_Read_Text_Delimiter+0 
	MOVLW       hi_addr(?lstr1_hae2+0)
	MOVWF       FARG_UART1_Read_Text_Delimiter+1 
	MOVLW       255
	MOVWF       FARG_UART1_Read_Text_Attempts+0 
	CALL        _UART1_Read_Text+0, 0
;hae2.c,23 :: 		Lcd_out(1,1, distancia);
	MOVLW       1
	MOVWF       FARG_Lcd_Out_row+0 
	MOVLW       1
	MOVWF       FARG_Lcd_Out_column+0 
	MOVLW       _distancia+0
	MOVWF       FARG_Lcd_Out_text+0 
	MOVLW       hi_addr(_distancia+0)
	MOVWF       FARG_Lcd_Out_text+1 
	CALL        _Lcd_Out+0, 0
;hae2.c,24 :: 		}
L_interrupt3:
;hae2.c,25 :: 		}
L_interrupt2:
;hae2.c,26 :: 		PIR1.RCIF = 0; // se borra el flag de la interrupción RCIE
	BCF         PIR1+0, 5 
;hae2.c,27 :: 		}
L_end_interrupt:
L__interrupt8:
	RETFIE      1
; end of _interrupt

_main:

;hae2.c,29 :: 		void main(){
;hae2.c,30 :: 		Lcd_Init();
	CALL        _Lcd_Init+0, 0
;hae2.c,31 :: 		UART1_Init(baund_rate);
	BSF         BAUDCON+0, 3, 0
	CLRF        SPBRGH+0 
	MOVLW       207
	MOVWF       SPBRG+0 
	BSF         TXSTA+0, 2, 0
	CALL        _UART1_Init+0, 0
;hae2.c,34 :: 		RCSTA.SREN = 1;
	BSF         RCSTA+0, 5 
;hae2.c,35 :: 		RCON.IPEN = 0; //se deshabilitan las interrupciones con prioridad (MikroC)
	BCF         RCON+0, 7 
;hae2.c,36 :: 		PIR1.RCIF = 0; //se pone a cero el flag de la interrupción RCIE
	BCF         PIR1+0, 5 
;hae2.c,37 :: 		PIE1.RCIE = 1; // se habilita la interrupción RCIE
	BSF         PIE1+0, 5 
;hae2.c,38 :: 		INTCON.PEIE = 1; //es de tipo peripheral
	BSF         INTCON+0, 6 
;hae2.c,39 :: 		INTCON.GIE = 1; // se habilitan las interrupciones en general
	BSF         INTCON+0, 7 
;hae2.c,41 :: 		while(1);
L_main4:
	GOTO        L_main4
;hae2.c,42 :: 		}
L_end_main:
	GOTO        $+0
; end of _main
