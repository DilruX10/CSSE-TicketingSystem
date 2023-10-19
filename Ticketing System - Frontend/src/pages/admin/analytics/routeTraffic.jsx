import Header from "../../../components/header.jsx";
import Sidebar from "../../../components/sidebar.jsx";

import axios from "axios";
import React, { useEffect, useState } from "react";

function Map() {
	const [routes, setRoutes] = useState([]);
	const [map, setMap] = useState(null);

	const colors = ["#FF5733", "#FFDB58", "#41EAD4", "#FF5E93", "#48CC45"];

	useEffect(() => {
		const directionsService = new window.google.maps.DirectionsService();
		const mapOptions = {
			center: { lat: 7.2906, lng: 80.6337 },
			zoom: 3,
			mapTypeId: "hybrid", // Set the map type to hybrid (satellite with labels)
		};

		const mapInstance = new window.google.maps.Map(
			document.getElementById("map"),
			mapOptions
		);
		setMap(mapInstance);

		axios
			.get(
				"https://digitalreader-api.onrender.com/api/v1/journeys/all/ongoing/countByRoute"
			)
			.then((res) => {
				setRoutes(res.data);

				// Render routes on the map based on the response data
				renderRoutesOnMap(res.data, mapInstance, directionsService);
			})
			.catch((err) => {
				console.log(err);
			});
	}, []);

	function renderRoutesOnMap(data, map, directionsService) {
		data.slice(0, 5).forEach((routeData, index) => {
			const { start, end, count } = routeData;
			const routeRequest = {
				origin: start,
				destination: end,
				travelMode: "DRIVING",
			};

			directionsService.route(routeRequest, function (result, status) {
				if (status === "OK") {
					const directionsRenderer =
						new window.google.maps.DirectionsRenderer({
							map: map,
							polylineOptions: {
								strokeColor: colors[index],
								strokeOpacity: 1,
								strokeWeight: 4,
							},
						});
					directionsRenderer.setDirections(result);

					// Create a custom marker with the same color as the route
					const marker = new window.google.maps.Marker({
						position: result.routes[0].legs[0].start_location,
						map: map,
						icon: {
							path: window.google.maps.SymbolPath.CIRCLE,
							fillColor: colors[index],
							fillOpacity: 1,
							strokeColor: colors[index],
							strokeWeight: 2,
							scale: 6,
						},
					});
				}
			});
		});
	}

	return (
		<div>
			<Header />
			<Sidebar />
			<div className="ml-[15%] mr-[1%]">
				{" "}
				<div
					style={{
						display: "flex",
						justifyContent: "center",
						alignItems: "center",
						flexDirection: "column",
					}}
				>
					<div
						style={{
							display: "flex",
							alignItems: "center",
							justifyContent: "center",
							flexDirection: "column",
							marginBottom: "10px",
						}}
					>
						<span
							style={{
								fontWeight: "bold",
								fontSize: "24px",
								fontFamily: "monospace",
							}}
						>
							Top 5 Bus Routes with Most Traffic
						</span>
						<div style={{ display: "flex", alignItems: "center" }}>
							{colors.map((color, index) => (
								<div
									style={{
										height: "30px",
										width: "30px",
										backgroundColor: color,
										display: "flex",
										alignItems: "center",
										justifyContent: "center",
										borderRadius: "40%",
										margin: "5px 6px",
										color: "#333",
									}}
								>
									{index + 1}
								</div>
							))}
						</div>
					</div>
					<div id="map" style={{ width: "1200px", height: "500px" }}></div>
					;
				</div>
			</div>
		</div>
	);
}

export default Map;
