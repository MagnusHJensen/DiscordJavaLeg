package dk.magnusjensen.discordjavaleg.entities;

import com.fasterxml.jackson.databind.node.ArrayNode;

import java.util.ArrayList;

public enum GuildFeature {

	INVITE_SPLASH(),
	VIP_REGIONS(),
	VANITY_URL(),
	VERIFIED(),
	PARTNERED(),
	COMMUNITY(),
	COMMERCE(),
	NEWS(),
	DISCOVERABLE(),
	FEATURABLE(),
	ANIMATED_ICON(),
	BANNER(),
	WELCOME_SCREEN_ENABLED(),
	MEMBER_VERIFICATION_GATE_ENABLED(),
	PREVIEW_ENABLED();

	GuildFeature() {

	}


	protected static ArrayList<GuildFeature> parseFeaturesFromArray(ArrayNode node) {
		ArrayList<GuildFeature> features = new ArrayList<>();
		node.forEach((featureString) -> {
			String feature = featureString.textValue();

			features.add(GuildFeature.valueOf(feature));
		});
		return features;
	}
}
