package ru.skillfactory.bot.tgbot.DTO;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.datatype.XMLGregorianCalendar;
import lombok.Data;


@XmlRootElement(name = "GetCursOnDateXML", namespace = "http://web.cbr.ru/")
@XmlAccessorType(XmlAccessType.FIELD)
//Указываем, как получить/записать значения в поля данного объекта
@Data //Геттеры и сеттеры
public class GetCursOnDateXml {

  @XmlElement(name = "On_date", required = true, namespace = "http://web.cbr.ru/")
  //Указание на то, в каком теге XML должно быть данное поле
  protected XMLGregorianCalendar onDate;
}
