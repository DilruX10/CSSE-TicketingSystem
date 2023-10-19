import axios from "axios";
import jsPDF from "jspdf";
import "jspdf-autotable";
import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import reportHeader from "../../../assets/reportHeader.png";
import Footer from "../../../components/footer.jsx";
import Header from "../../../components/header.jsx";
import Sidebar from "../../../components/sidebar.jsx";

export default function ViewAllPage() {
	const [routes, setRoutes] = useState([]);

	// get data
	const getRoutes = async () => {
		try {
			const response = await axios.get(
				"http://localhost:8080/api/routes/view/all"
			);
			setRoutes(response.data);
		} catch (error) {
			const errorRes = error.response;
			console.log(errorRes);
			window.alert(errorRes.data.msg);
		}
	};

	// delete record
	const handleDelete = async (e, id) => {
		e.preventDefault();
		const confirm = window.confirm("Do you want to delete this record?");

		if (confirm) {
			try {
				const response = await axios.delete(
					"http://localhost:8080/api/routes/delete/" + id
				);
				window.alert(response.data.msg);
				getRoutes();
			} catch (error) {
				const errorRes = error.response;
				console.log(errorRes);
				window.alert(errorRes.data.msg);
			}
		}
	};

	useEffect(() => {
		getRoutes();
	}, []);

	// data table
	const renderTable = () => {
		return routes.map((route) => {
			return (
				<tr
					className="p-2 h-fit border-b-2 border-neutral-200"
					key={route.routeId}
				>
					<td align="center" className="p-2">
						{" "}
						<i className="lni lni-map"></i>
					</td>
					<td align="center" className="p-2">
						{route.routeId}
					</td>
					<td align="center" className="p-2">
						{route.title}
					</td>
					<td align="center" className="p-2">
						{route.origin}
					</td>
					<td align="center" className="p-2">
						{route.destination}
					</td>
					{/* Buttons */}
					<td align="center" className="w-fit p-2 flex items-center">
						{/* View  */}
						<Link to={`/viewRoute/${route.routeId}`}>
							<span className="text-neutral-600 border-2 border-cyan-500 border-solid rounded-lg h-fit w-fit text-center px-2 py-1  mx-2 cursor-pointer hover:bg-cyan-500 hover:text-white">
								<i className="lni lni-eye"></i> View
							</span>
						</Link>
						&nbsp;
						{/* Edit  */}
						<Link to={`/editRoute/${route.routeId}`}>
							<span className="text-neutral-600 border-2 border-amber-500 border-solid rounded-lg h-fit w-fit text-center px-2 py-1  mx-2 cursor-pointer hover:bg-amber-500 hover:text-white">
								<i className="lni lni-pencil"></i> Edit
							</span>
						</Link>
						&nbsp;
						{/* Delete  */}
						<span
							className="text-neutral-600 border-2 border-red-500  border-solid rounded-lg h-fit w-fit text-center px-2 py-1 mx-2 cursor-pointer hover:text-white hover:bg-red-500 "
							onClick={(e) => handleDelete(e, route.routeId)}
						>
							<i className="lni lni-trash-can"></i> Delete
						</span>
					</td>
				</tr>
			);
		});
	};

	//generate pdf
	const generatePDF = () => {
		const doc = new jsPDF();
		const tableRows = [];

		const currentDate = new Date();
		const year = currentDate.getFullYear();
		const month = String(currentDate.getMonth() + 1).padStart(2, "0");
		const day = String(currentDate.getDate()).padStart(2, "0");
		const formattedDate = `${day}-${month}-${year}`;

		// Add the logo image to the document
		const logoWidth = 180;
		const logoHeight = 25;
		const pageWidth = doc.internal.pageSize.getWidth();
		const logoX = (pageWidth - logoWidth) / 2;
		doc.addImage(reportHeader, "PNG", logoX, 10, logoWidth, logoHeight); // (image, type, x, y, width, height)

		// Add custom text next to the report name
		doc.setFont("helvetica", "bold");
		doc.setFontSize(20);

		//Change report name accordingly
		doc.text("Routes Report", pageWidth / 2, 60, { align: "center" });
		// Underline the text
		const textWidth =
			(doc.getStringUnitWidth("Routes Report") *
				doc.internal.getFontSize()) /
			doc.internal.scaleFactor;
		doc.setLineWidth(0.5);
		doc.line(
			pageWidth / 2 - textWidth / 2,
			63,
			pageWidth / 2 + textWidth / 2,
			63
		);

		doc.setFont("arial", "normal");
		doc.setFontSize(12);
		doc.text("Date: " + formattedDate, 195, 75, { align: "right" });

		// Add gap
		const gap = 5;
		let y = 80;

		// Add table headers
		const headers = ["Route ID", "Route No,", "Origin", "Destination"];
		tableRows.push(headers);

		// Add table data
		routes.forEach((route) => {
			const data = [
				route.routeId,
				route.title,
				route.origin,
				route.destination,
			];
			tableRows.push(data);
		});

		// Add table to PDF document
		doc.autoTable({
			head: [tableRows[0]],
			body: tableRows.slice(1),
			startY: y + gap,
		});

		// Save PDF file
		doc.save("TBS-Routes_List.pdf");
	};

	return (
		<div>
			<Header />
			<Sidebar />

			{/* Content starts from here */}
			<div className="ml-[15%] mr-[1%]">
				<center>
					{/* title */}
					<h1
						id="title"
						className=" mt-4 text-5xl tracking-wide text-neutral-600 "
					>
						List of All Routes
					</h1>

					<br />
					<hr />

					{/* Menu Bar */}
					<div className="mt-2 mb-2 flex flex-row items-center">
						{/* Search Bar  */}
						<div className="w-[25%] flex items-center float-left text-neutral-600 border-2 border-grey-500 border-solid rounded-full h-fit text-center px-2 py-1 m-2">
							<input
								type="text"
								name="name"
								className="form-control px-4 py-2 rounded-full w-full h-8px"
								placeholder="Search.."
							/>
							&nbsp;
							<span className="float-right border-2 border-neutral-400  border-solid rounded-full h-fit w-fit text-center px-2 py-1 cursor-pointer hover:text-white hover:bg-neutral-500">
								<i className="lni lni-search"></i>
							</span>
						</div>

						{/* Report Generation  */}
						<button
							className="w-[10%] float-right text-neutral-600 border-2 border-teal-500 border-solid rounded-lg h-fit w-fit text-center px-2 py-1 m-2 ml-[57.5%] cursor-pointer hover:text-white hover:bg-teal-500"
							onClick={() => generatePDF()}
						>
							<i className="lni lni-exit-down"> </i> Download
						</button>

						{/* Add  */}
						<Link to="/newRoute">
							<div className="w-[10%] float-right text-neutral-600 border-2 border-green-500  border-solid rounded-lg h-fit w-fit text-center px-2 py-1 m-2 cursor-pointer hover:text-white hover:bg-green-500">
								<i className="lni lni-plus"> </i> Add
							</div>
						</Link>
					</div>

					{/* Table  */}
					<table
						id="routes"
						className=" w-full  border-collapse border-spacing-2 "
					>
						<thead className="bg-slate-200">
							<tr className="h-8 space-x-4">
								<th className="w-[5%]">&nbsp;</th>
								<th className=" ">ID</th>
								<th className=" ">No.</th>
								<th className=" ">Origin</th>
								<th className=" ">Destination</th>
								<th className="w-[25%]">&nbsp;</th>
							</tr>
						</thead>
						<tbody>{renderTable()}</tbody>
					</table>
				</center>
			</div>
			{/* Content ends from here */}

			<Footer />
		</div>
	);
}
