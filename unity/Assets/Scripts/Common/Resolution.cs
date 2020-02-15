using UnityEngine;

namespace Common {
	public enum Resolution {

		Portrait,
		Landscape

	}

	public class ResolutionUtils {

		public static Resolution getResolution(int width, int height) {
			return width < height ? Resolution.Portrait : Resolution.Landscape;
		}

		public static Resolution getResolution() {
			return getResolution(Screen.width, Screen.height);
		}

	}
}