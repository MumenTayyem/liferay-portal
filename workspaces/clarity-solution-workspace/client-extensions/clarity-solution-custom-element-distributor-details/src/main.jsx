import React, { StrictMode } from 'react';
import * as ReactDOM from 'react-dom';

import App from './App.jsx'
import './index.css'

const Main = () => (
	<StrictMode>
		<App />
	</StrictMode>
);

class WebComponent extends HTMLElement {
	connectedCallback() {
		ReactDOM.render(React.createElement(Main), this);
	}
}

const ELEMENT_ID = 'clarity-custom-element-distributor-details';

if (!customElements.get(ELEMENT_ID)) {
	customElements.define(ELEMENT_ID, WebComponent);
}
