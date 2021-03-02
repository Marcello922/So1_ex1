package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

public class RedesController {
	
		public void ip(String so) {
			String comando = "";
			if(so.contains("Windows")) {
				comando = "ipconfig";
			}else if(so.contains("Linux")) {
				comando = "ifconfig";
			}			
			try {
				Process p = Runtime.getRuntime().exec(comando);
				InputStream fluxo = p.getInputStream();
				InputStreamReader leitor = new InputStreamReader(fluxo);
				BufferedReader buffer = new BufferedReader(leitor);
				String linha = buffer.readLine();
				String aux = "";
				
				if(so.contains("Windows")) {
				
					while (linha != null) {
						if(linha.contains("Ethernet") && !aux.contains("Ethernet")) {
							aux += linha + "\n";
						}
						else if(linha.contains("IPv4")) {
							aux += linha;
							System.out.println(aux);
							aux = "";
						}
						linha = buffer.readLine();
					}
				}
					
				if(so.contains("Linux")) {
					while(linha != null) {
						linha.split("\\s.*");
						if(linha.contains("enp")) {
							aux += linha + "\n";
							while(linha != null) {
								if(linha.contains("inet ")) {
									linha.split("netmask.*");
									aux += linha;
									System.out.println(aux);
									aux = "";
								}
								else if(linha.contains("TX errors")) {
									aux = "";
								}
								linha = buffer.readLine();								
							}
							
						}
						else {
							linha = buffer.readLine();
						}
					}
				}
				
				buffer.close();
				leitor.close();
				fluxo.close();
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
		
		public void ping(String os) {
			String comando = "";
			if(os.contains("Windows")) {
				comando = "ping -n 10 www.google.com"; 
			}
			else if(os.contains("Linux")) {
				comando = "ping -c 10 www.google.com";
			}
			try {
				Process p = Runtime.getRuntime().exec(comando);
				InputStream fluxo = p.getInputStream();
				InputStreamReader leitor = new InputStreamReader(fluxo);
				BufferedReader buffer = new BufferedReader(leitor);
				String linha = buffer.readLine();	
				if(os.contains("Windows")) {
					while(linha != null) {
						if(linha.contains("tempo") || linha.contains("time")) {
							linha = Arrays.toString(linha.split(".*bytes=\\d*"));
							linha = Arrays.toString(linha.split("TTL.*"));
							System.out.println(linha);
							linha = buffer.readLine();
						}
						else if((linha.contains("dia = "))) {

							System.out.println(Arrays.toString(linha.split(".*,")));
							
							linha = buffer.readLine();
						}
						else {
								linha = buffer.readLine();																			
						}
					}
					
					
				} else if(os.contains("Linux")) {
					while(linha != null) {
						if(linha.contains("time")) {
							System.out.println(Arrays.toString(linha.split(".*117")));
						}
						else if(linha.contains("avg")) {
							System.out.println(linha);
						}
						linha = buffer.readLine();
					}
				}
				
				buffer.close();
				leitor.close();
				fluxo.close();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}

