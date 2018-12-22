/*package com.suam.util;
 
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.xml.datatype.XMLGregorianCalendar;
import org.apache.commons.lang.time.DateUtils;

public class CalendarUtil {

    private static final String DEFAULT_CALENDAR_DATE_FORMAT = "dd/MM/yyyy";

    private static final String DEFAULT_CALENDAR_TIME_FORMAT = "dd/MM/yyyy HH:mm";

    *//**
     * Método responsável por retornar o tempo formatado
     *
     * @param calendar
     * @return
     *//*
    public static String timeFormat(Calendar calendar) {
        return format(calendar, DEFAULT_CALENDAR_TIME_FORMAT);
    }

    *//**
     * Método responsável por retornar a data formatada
     *
     * @param calendar
     * @return
     *//*
    public static String dateFormat(Calendar calendar) {
        return format(calendar, DEFAULT_CALENDAR_DATE_FORMAT);
    }

    *//**
     * Método responsável por retornar a data formatada
     *
     * @param calendar
     * @return
     *//*
    public static String dateFormat(XMLGregorianCalendar calendar) {
        return format(calendar.toGregorianCalendar(), DEFAULT_CALENDAR_DATE_FORMAT);
    }

    *//**
     * Método responsável por retornar a string formatada
     *
     * @param calendar
     * @param format
     * @return
     *//*
    public static String format(Calendar calendar, String format) {
        if (calendar == null) {
            return "";
        }
        return format(calendar.getTime(), format);
    }

    *//**
     * Método responsável por retornar a string formatada
     *
     * @param date
     * @param format
     * @return
     *//*
    public static String format(Date date, String format) {

        if (date == null) {
            return "";
        }

        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    *//**
     * Método responsável por retornar a quantidade de dias de acordo com o intervalo de tempo fornecido
     *
     * @param start
     * @param end
     * @return
     *//*
    public static Long getPeriodDays(Calendar start, Calendar end) {
        Calendar startDate = Calendar.getInstance();
        startDate.setTime(start.getTime());
        startDate.add(Calendar.DAY_OF_MONTH, -1);

        Calendar endDate = Calendar.getInstance();
        endDate.setTime(end.getTime());

        Long days = ((endDate.getTimeInMillis() - startDate.getTimeInMillis()) / (1000 * 60 * 60 * 24));
        return days;
    }

    *//**
     * Método responsável por verificar se dentre o intervalo fornecido existe algum tipo de feriado
     *
     * @param start
     * @param end
     * @return
     *//*
    public static boolean intervalHasWeekend(Calendar start, Calendar end) {

        start = (Calendar) start.clone();
        end = (Calendar) end.clone();

        for (Date date = start.getTime(); start.before(end); start.add(Calendar.DATE, 1), date = start.getTime()) {

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);

            int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

            if (dayOfWeek == Calendar.SATURDAY || dayOfWeek == Calendar.SUNDAY) {
                return true;
            }

        }

        return false;

    }

    *//**
     * Método responsável por verificar se o período está entre o dia recebido
     *
     * @param data
     * @param dataInicio
     * @param dataFim
     * @return
     *//*
    public static boolean between(XMLGregorianCalendar data, XMLGregorianCalendar dataInicio, XMLGregorianCalendar dataFim) {

        return between(data.toGregorianCalendar(), dataInicio.toGregorianCalendar(), dataFim.toGregorianCalendar());

    }


    public static boolean between(Calendar data, Calendar dataInicio, Calendar dataFim) {

        return between(data.getTime(), dataInicio.getTime(), dataFim.getTime());

    }

    *//**
     * Método responsável por verificar se o período está entra o dia recebido
     *
     * @param data
     * @param periodoInicio
     * @param periodoFim
     * @return
     *//*
    public static boolean between(Date data, Date periodoInicio, Date periodoFim) {

        data = DateUtils.truncate(data, Calendar.DATE);
        periodoInicio = DateUtils.truncate(periodoInicio, Calendar.DATE);
        periodoFim = DateUtils.truncate(periodoFim, Calendar.DATE);

        if (data.compareTo(periodoInicio) >= 0 && data.compareTo(periodoFim) <= 0) {
            return true;
        }

        return false;

    }

    *//**
     * Método responsável por criar um objeto Calendar a partir de uma data em milissegundos
     *
     * @param dataTimestamp
     * @return
     *//*
    public static Calendar getCalendar(Long dataTimestamp) {

        Calendar calendar = null;

        if (dataTimestamp != null) {

            Timestamp timestamp = new Timestamp(dataTimestamp);

            Date data = new Date(timestamp.getTime());

            calendar = Calendar.getInstance();

            calendar.setTime(data);
        }

        return calendar;

    }

	*//**
     * Método responsável por criar um objeto Calendar com a hora, minuto, segundo e milissegundo zerados.
     *
     * @param dataTimestamp
     * @return
     *//*
    public static Calendar getInicioDia(Long dataTimestamp) {

        Calendar dataInicioDia = Calendar.getInstance();
        dataInicioDia.setTime(new Calendar.Builder().setInstant(dataTimestamp).build().getTime());
        dataInicioDia.set(Calendar.HOUR_OF_DAY, 0);
        dataInicioDia.set(Calendar.MINUTE, 0);
        dataInicioDia.set(Calendar.SECOND, 0);
        dataInicioDia.set(Calendar.MILLISECOND, 0);
        return dataInicioDia;

    }

	*//**
     * Método responsável por criar um objeto Calendar com os últimos valores de hora, minuto, segundo e milissegundo o dia.
     *
     * @param dataTimestamp
     * @return
     *//*
    public static Calendar getFinalDia(Long dataTimestamp) {

        Calendar dataFinalDia = Calendar.getInstance();
        dataFinalDia.setTime(new Calendar.Builder().setInstant(dataTimestamp).build().getTime());
        dataFinalDia.set(Calendar.HOUR_OF_DAY, 23);
        dataFinalDia.set(Calendar.MINUTE, 59);
        dataFinalDia.set(Calendar.SECOND, 59);
        dataFinalDia.set(Calendar.MILLISECOND, 999);
        return dataFinalDia;
    }

	*//**
     * Método responsável por criar um objeto Calendar baseado em uma data.
     *
     * @param date
     * @return
     *//*
    public static Calendar toCalendar(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal;
    }
}
*/