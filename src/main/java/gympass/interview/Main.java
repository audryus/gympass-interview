package gympass.interview;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {
	static final DateFormat hora = new SimpleDateFormat("HH:mm:ss.SSS");
	static final DateFormat tempo = new SimpleDateFormat("m:ss.SSS");
	
	public static void main(String[] args) throws ParseException, FileNotFoundException {
		InputStream resourceAsStream = Main.class.getClassLoader().getResourceAsStream("corrida.txt");
		
		//Caso passe o arquivo nos args.
		if (args != null && args.length > 0) {
			String logpath = args[0];
			File f = new File(logpath);
			if (f.exists()) {
				resourceAsStream = new FileInputStream(f);
			}
		}
		
		Scanner scanner = new Scanner(resourceAsStream);
		//Ignorar o cabeçalho.
		scanner.nextLine();

		List<Piloto> pilotos = new ArrayList<Piloto>();
		Piloto piloto;
		Map<String, Long> tempoTotal = new HashMap<String, Long>();
		while(scanner.hasNext()) {
			String[] split = scanner.nextLine().split("\\s+");
			piloto = new Piloto();
			piloto.hora = Calendar.getInstance();
			piloto.hora.setTime(hora.parse(split[0]));
			piloto.id = split[1];
			piloto.nome = split[3];
			
			piloto.volta = Integer.valueOf(split[4]);
			
			
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(tempo.parse(split[5]));
			piloto.tempo = calendar.getTimeInMillis();
			
			Calendar calendar2 = Calendar.getInstance();
			calendar2.setTime(piloto.hora.getTime());
			calendar2.add(Calendar.MINUTE, -1*calendar.get(Calendar.MINUTE));
			calendar2.add(Calendar.SECOND, -1*calendar.get(Calendar.SECOND));
			calendar2.add(Calendar.MILLISECOND, -1*calendar.get(Calendar.MILLISECOND));
			piloto.inicio = calendar2;
			
			if (!tempoTotal.containsKey(piloto.id)) {
				tempoTotal.put(piloto.id, 0L);
			} 
			tempoTotal.put(piloto.id, tempoTotal.get(piloto.id) + piloto.tempo);
			
			piloto.velocidade = Float.parseFloat(split[6].replace(",", "."));
			pilotos.add(piloto);
		}
		Set<Piloto> pilotosUnicos = pilotos.stream().sorted(Comparator.comparing(Piloto::getVolta).reversed()).collect(Collectors.toSet());
		pilotos = pilotosUnicos.stream().collect(Collectors.toList());
		pilotos.stream().forEach(p -> p.tempoTotal = tempoTotal.get(p.id));
		pilotos = pilotos.stream().sorted().collect(Collectors.toList());
		
		for(int i = 0; i < pilotos.size(); i++) {
			System.out.print(i + 1);
			System.out.print(", ");
			System.out.print(pilotos.get(i).id);
			System.out.print(", ");
			System.out.print(pilotos.get(i).nome);
			System.out.print(", ");
			System.out.print(pilotos.get(i).volta);
			System.out.print(", ");
			Date date = new Date(pilotos.get(i).tempoTotal);
			System.out.println(tempo.format(date));
		}
		
	}
}
