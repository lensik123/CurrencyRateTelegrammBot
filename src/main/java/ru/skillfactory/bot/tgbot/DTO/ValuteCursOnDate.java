package ru.skillfactory.bot.tgbot.DTO;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.Data;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "ValuteCursOnDate")
@Data
public class ValuteCursOnDate {

  @XmlElement(name = "Vname")
  private String name;

  @XmlElement(name = "Vnom")
  private int nominal;

  @XmlElement(name = "Vcurs")
  private double course;

  @XmlElement(name = "Vcode")
  private String code;

  @XmlElement(name = "VchCode")
  private String chCode;
}
