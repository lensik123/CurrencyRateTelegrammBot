package ru.skillfactory.bot.tgbot.DTO;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.Data;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "GetCursOnDateXmlResult")
@Data
public class GetCursOnDateXmlResult {

  @XmlElementWrapper(name = "ValuteData", namespace = "")
  @XmlElement(name = "ValuteCursOnDate", namespace = "")
  List<ValuteCursOnDate> valuteData = new ArrayList<>();


}
