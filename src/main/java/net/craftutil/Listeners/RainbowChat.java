package net.craftutil.Listeners;

import java.awt.Color;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import io.papermc.paper.event.player.AsyncChatEvent;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;

public class RainbowChat implements Listener {

	// private static final float HUE_STEP = 1.0f / 360.0f;

	@EventHandler
	public void onPlayerChat(AsyncChatEvent e) {
		TextComponent.Builder builder = Component.text();
			String message = PlainTextComponentSerializer.plainText().serialize(e.message());
			for (int j = 0; j < message.length(); j++) {
				char c = message.charAt(j);
				float hue = (0.8f - ((float) j / (float) message.length()) * 0.8f) % 1.0f;
				TextColor color = TextColor.color(Color.HSBtoRGB(hue, 1.0f, 1.0f));
				builder.append(Component.text(String.valueOf(c), color));
			}

		e.message(builder.build());
	}
}