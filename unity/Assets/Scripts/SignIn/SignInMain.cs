using System.Collections;
using System.Collections.Generic;
using Common;
using UnityEngine;
using Resolution = Common.Resolution;

namespace SignIn {
	public class SignInMain : MonoBehaviour {

		[SerializeField]
		private GameObject portrait;

		[SerializeField]
		private GameObject landscape;

		private Resolution _resolution;

		private void Start() {
			if (portrait == null) {
				portrait = GameObject.Find("Portrait");
			}

			if (landscape == null) {
				landscape = GameObject.Find("Landscape");
			}

			_resolution = ResolutionUtils.getResolution();
			ChangeResolution(_resolution);
		}

		private void Update() {
			UpdateResolution();
		}

		/**
		 * 레졸루션이 변경되었는지 검사
		 */
		private void UpdateResolution() {
			var resolution = ResolutionUtils.getResolution();
			if (_resolution == resolution)
				return;

			_resolution = resolution;
			ChangeResolution(_resolution);
		}

		/**
		 * 해당 레졸루션으로 변경
		 */
		private void ChangeResolution(Resolution resolution) {
			if (resolution == Resolution.Landscape) {
				landscape.SetActive(true);
				portrait.SetActive(false);
			} else {
				landscape.SetActive(false);
				portrait.SetActive(true);
			}
		}

	}
}