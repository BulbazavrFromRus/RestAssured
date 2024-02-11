package kucoin;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TickerData {
   private String  symbol;
   private String   symbolName;
    private String buy;
    private String         bestBidSize;
    private String sell;
    private String         bestAskSize;
    private String changeRate;
    private String        changePrice;
    private String high;
    private String        low;
    private String vol;
    private String        volValue;
    private String last;
    private String         averagePrice;
    private String takerFeeRate;
    private String        makerFeeRate;
    private  String takerCoefficient;
    private String        makerCoefficient;

    public TickerData() {
    }

    @JsonCreator
    public TickerData(@JsonProperty("symbol") String symbol,
                      @JsonProperty("symbolName") String symbolName,
                      @JsonProperty("buy") String buy,
                      @JsonProperty("bestBidSize") String bestBidSize,
                      @JsonProperty("sell") String sell,
                      @JsonProperty("bestAskSize") String bestAskSize,
                      @JsonProperty("changeRate") String changeRate,
                      @JsonProperty("changePrice") String changePrice,
                      @JsonProperty("high") String high,
                      @JsonProperty("low") String low,
                      @JsonProperty("vol") String vol,
                      @JsonProperty("volValue") String volValue,
                      @JsonProperty("last") String last,
                      @JsonProperty("averagePrice") String averagePrice,
                      @JsonProperty("takerFeeRate") String takerFeeRate,
                      @JsonProperty("makerFeeRate") String makerFeeRate,
                      @JsonProperty("takerCoefficient")  String takerCoefficient,
                      @JsonProperty("makerCoefficient") String makerCoefficient) {
        this.symbol = symbol;
        this.symbolName = symbolName;
        this.buy = buy;
        this.bestBidSize = bestBidSize;
        this.sell = sell;
        this.bestAskSize = bestAskSize;
        this.changeRate = changeRate;
        this.changePrice = changePrice;
        this.high = high;
        this.low = low;
        this.vol = vol;
        this.volValue = volValue;
        this.last = last;
        this.averagePrice = averagePrice;
        this.takerFeeRate = takerFeeRate;
        this.makerFeeRate = makerFeeRate;
        this.takerCoefficient = takerCoefficient;
        this.makerCoefficient = makerCoefficient;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getSymbolName() {
        return symbolName;
    }

    public String getBuy() {
        return buy;
    }

    public String getBestBidSize() {
        return bestBidSize;
    }

    public String getSell() {
        return sell;
    }

    public String getBestAskSize() {
        return bestAskSize;
    }

    public String getChangeRate() {
        return changeRate;
    }

    public String getChangePrice() {
        return changePrice;
    }

    public String getHigh() {
        return high;
    }

    public String getLow() {
        return low;
    }

    public String getVol() {
        return vol;
    }

    public String getVolValue() {
        return volValue;
    }

    public String getLast() {
        return last;
    }

    public String getAveragePrice() {
        return averagePrice;
    }

    public String getTakerFeeRate() {
        return takerFeeRate;
    }

    public String getMakerFeeRate() {
        return makerFeeRate;
    }

    public String getTakerCoefficient() {
        return takerCoefficient;
    }

    public String getMakerCoefficient() {
        return makerCoefficient;
    }
}



