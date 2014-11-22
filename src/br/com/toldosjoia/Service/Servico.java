package br.com.toldosjoia.Service;

import java.util.Calendar;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class Servico extends BroadcastReceiver {


	@Override
	public void onReceive(Context contexto, Intent intent) {

		agendar(contexto);

	}


	private void agendar(Context contexto) {
		Intent it = new Intent("ATUALIZAR_TOLDOSJOIA");
		
		PendingIntent p = PendingIntent.getService(contexto, 0, it, PendingIntent.FLAG_CANCEL_CURRENT);
		
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(System.currentTimeMillis());
		c.add(Calendar.SECOND, 30);
		AlarmManager alarme = (AlarmManager) contexto.getSystemService(Context.ALARM_SERVICE);
		long tempo = c.getTimeInMillis();
		alarme.setRepeating(AlarmManager.RTC_WAKEUP, tempo,5*1000, p);
	}
}
