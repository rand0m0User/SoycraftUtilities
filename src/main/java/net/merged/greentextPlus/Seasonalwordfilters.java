package net.merged.greentextPlus;

import java.time.LocalDate;
import java.time.Month;
import java.util.regex.Pattern;

import net.craftutil.Settings;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextReplacementConfig;

public class Seasonalwordfilters {

	private Seasonalwordfilters() {
		// > コンストラクタの記述が無いとSonarQubeがCode Smellと判定してしまうので明示的に何もしないコンストラクタを実装
		// <japmutt.png
	}

	public static Component apply(Component message) {
		LocalDate currentDate = LocalDate.now();
		if ((currentDate.getMonth() == Month.APRIL && currentDate.getDayOfMonth() == 1) || Settings.AprilFoolsOveride) {
			String[][] fourChanWordfilters = new String[][] { new String[] { "basedja", "soyja" },
					new String[] { "BASEDJA", "SOYJA" }, new String[] { "basedle", "soyle" },
					new String[] { "BASEDLE", "SOYLE" }, new String[] { "onions ", "soy\\s*" },
					new String[] { "ONIONS ", "SOY\\s*" }, new String[] { "desu ", "tbh\s*" },
					new String[] { "DESU ", "TBH\s*" } };
			message = filter(message, fourChanWordfilters);
		}

		// Halloween themed word filters
		if (currentDate.getMonth() == Month.NOVEMBER) {
			String[][] novemberWordfilters = new String[][] { new String[] { "candy", Pattern.quote("gem") }, };
			message = filter(message, novemberWordfilters);
		}

		// Christmas themed word filters
		if (currentDate.getMonth() == Month.DECEMBER || Settings.FestiveWordfiltersOveride) {
			// i fucking love macro programming!!!
			//
			// String[] list = new String(load("wikipost.txt")).split("\r\n");
			// for (String l : list) {
			// String[] split = l.split(Pattern.quote(" > "));
			// print(String.format("new String[] { \"%s\", Pattern.quote(\"%s\") },",
			// split[1], split[0]));
			// }

			String[][] decemberWordfilters = new String[][] { new String[] { "good boy", Pattern.quote("chud") },
					new String[] { "good boys", Pattern.quote("chuds") },
					new String[] { "bad boy", Pattern.quote("nigger") },
					new String[] { "santa claus", Pattern.quote("froot") },
					new String[] { "grinch", Pattern.quote("tranny") },
					new String[] { "krampus", Pattern.quote("troon") },
					new String[] { "grinches", Pattern.quote("trannies") },
					new String[] { "eggnog", Pattern.quote("soylent") },
					new String[] { "SPIRIT OF CHRISTMAS", Pattern.quote("ratio") },
					new String[] { "Licorice Candy", "[bB8\u0441\u0412]\s*[bB8\u0441\u0412]\s*[cC\u0421\u0441pP]" },
					new String[] { "Vanilla Candy", "[bB8\u0441\u0412]\s*[wW]\s*[cC\u0421\u0441pP]" },
					new String[] { "south pole", "4cuck|4chan|'cuck|4chon" },
					new String[] { "Santa's workshop", "sharty|soyjak.party|'party|party" },
					new String[] { "gift", Pattern.quote("gem") },
					new String[] { "The star at the top of the christmas tree", Pattern.quote("gemerald") },
					new String[] { "coal[it just is, ok?]", Pattern.quote("coal") },
					new String[] { "We have gathered here to celebrate Christmas, the day when Jesus Christ was born.",
							"[Ii][Tt][Tt]" },
					new String[] { "MERRY CHRISTMAS", "kys|kill yourself" },
					new String[] { "jack frost", Pattern.quote("zog") },
					new String[] { "snowman", Pattern.quote("faggot") },
					new String[] { "hungry", Pattern.quote("slut") }, new String[] { "scrooge", Pattern.quote("jew") },
					new String[] { "scrooges", "jews|jooz" }, new String[] { "santa's helper", "janny|mod" },
					new String[] { "Rudolph the Red Nosed reindeer", Pattern.quote("samefag") },
					new String[] { "Believe in the magic of christmas!", "fuck off|fuck you" },
					new String[] { "Let your heart be bright!", Pattern.quote("die") },
					new String[] { ", Rejoice! (even though is unchanged)", "though|doe|thoughever|however|albeit" },
					new String[] { "snowball fight", Pattern.quote("raid") },
					new String[] { "snowball fighting", Pattern.quote("raiding") },
					new String[] { "deliver presents", Pattern.quote("dox") },
					new String[] { "merry", Pattern.quote("calm") }, new String[] { "HOHOHO!", "bump|bumo" },
					new String[] { "bauble", "filter|worldfilter" },
					new String[] { "'Tis the season", Pattern.quote("it's over") },
					new String[] { "elf", Pattern.quote("jak") }, new String[] { "elves", Pattern.quote("jaks") },
					new String[] { "festive", "based|keyed" }, new String[] { "killjoy", Pattern.quote("cringe") },
					new String[] { "milk and cookies", Pattern.quote("meds") },
					new String[] { "caroler", Pattern.quote("ghoul") },
					new String[] { "smiling", Pattern.quote("seething") },
					new String[] { "smile", Pattern.quote("seethe") },
					new String[] { "celebrating", Pattern.quote("coping") },
					new String[] { "celebrate", Pattern.quote("cope") },
					new String[] { "feasting", Pattern.quote("dilating") },
					new String[] { "feast", Pattern.quote("dilate") }, new String[] { "Reindeer", "incel|chudcel" },
					new String[] { "Reindeers", "incel|chudcels" },
					new String[] { "in the naughty list", Pattern.quote("banned") },
					new String[] { "You have no gifts under your christmas tree", "[Yy][Ww][Nn][Bb][Aa][Ww]" },
					new String[] { "easter", Pattern.quote("twitter") },
					new String[] { "halloween", Pattern.quote("'cord") },
					new String[] { "bread", Pattern.quote("thread") },
					new String[] { "raisin", Pattern.quote("shit") }, };
			message = filter(message, decemberWordfilters);
		}

		return message;
	}

	private static Component filter(Component message, String[][] filters) {
		for (String[] filter : filters) {
			message = message
					.replaceText(TextReplacementConfig.builder().replacement(filter[0]).match(filter[1]).build());
		}
		return message;
	}
}