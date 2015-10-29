package org.p4p.backpocketdriver.driverlog.resource;

import java.util.Collection;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.p4p.backpocketdriver.driverlog.model.Journey;
import org.p4p.backpocketdriver.driverlog.model.JourneyStatistics;
import org.p4p.backpocketdriver.driverlog.service.JourneyService;


@Path("/journey")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class JourneyResource {
	
	private JourneyService journeyService = new JourneyService();	
	
	@GET
	@Path("/{userName}")
	public Collection<Journey> getAllJourneys(@PathParam("userName") String userName){
		return journeyService.getAllJourneys(userName);
	}

	@GET
	@Path("/journeys/{journeyId}")
	public Journey getJourney(@PathParam("journeyId") int journeyId){
		Journey journey =  journeyService.getJourney(journeyId);
		return journey;
	}
	
	
	@DELETE
	@Path("/{journeyId}")
	public Journey deleteJourney(@PathParam("journeyId") int journeyId){
		Journey journey =  journeyService.getJourney(journeyId);
		journeyService.removeJourney(journeyId);
		return journey;
	}
	
	@POST
	@Path("/{userName}")
	public Journey addJourney(Journey journey, @PathParam("userName") String userName){
		journeyService.addJourney(journey, userName);
		
		return journey;
	}
	
	@GET
	@Path("/statistics/{journeyId}")
	public JourneyStatistics getStatsForJourney(@PathParam("journeyId") int journeyId){
		return journeyService.getStatsForJourney(journeyId);
	}
	
	@POST
	@Path("/statistics/{journeyId}")
	public JourneyStatistics addStatsForJourney(JourneyStatistics js, @PathParam("journeyId") int journeyId){
		journeyService.addJourneyStatistic(journeyId, js);
		return js;
	}

	@GET
	@Path("/totalStatistics/{userName}")
	public Collection<JourneyStatistics> getAllJourneyStatistics(@PathParam("userName") String userName){
		return journeyService.getAllJourneyStatistics(userName);
	}
	

	

}
