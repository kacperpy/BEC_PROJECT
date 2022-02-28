package dk.bec.bookanything.service;

import dk.bec.bookanything.model.DayOpenEntity;
import dk.bec.bookanything.repository.DayOpenRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class DayOpenService {
    DayOpenRepository dayOpenRepository;

    public DayOpenService(DayOpenRepository dayOpenRepository) {
        this.dayOpenRepository = dayOpenRepository;
    }

    public Optional<DayOpenEntity> getDayOpenById(Long id)
    {
        return dayOpenRepository.findById(id);
    }

    public Optional<DayOpenEntity> addDayOpen(DayOpenEntity dayOpenEntity)
    {
        return Optional.of(dayOpenRepository.save(dayOpenEntity));
    }

    public Optional<DayOpenEntity> modifyDayOpen(DayOpenEntity dayOpenEntity)
    {
        return Optional.of(dayOpenRepository.save(dayOpenEntity));
    }

    public void deleteDayOpen(Long id){
        dayOpenRepository.deleteById(id);
    }
}
