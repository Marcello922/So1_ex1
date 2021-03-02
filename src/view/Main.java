package view;

import javax.swing.JOptionPane;


import controller.RedesController;

public class Main {
		
		public static void main(String[] args) {
			
			RedesController c = new RedesController();
			String os = System.getProperty("os.name");
			int menu;
			do {
				menu = Integer.parseInt(JOptionPane.showInputDialog("Selecione a ação: \n 1 - Lista de adaptdores Ethernet \n 2 - Media de Ping \n 3 - Sair."));				
			
			switch(menu) {
			case 1:
				c.ip(os);
				break;
			case 2:
				c.ping(os);
				break;
			case 3:
				break;
			default:
				JOptionPane.showMessageDialog(null,"Opção Inválida!");
			}
			}while(menu != 3);
		}
		

}
