package rentCars.service;

import lombok.NoArgsConstructor;
import rentCars.dao.ClientDao;
import rentCars.dto.ClientDto;
import rentCars.dto.CreateDto.CreateClientDto;
import rentCars.mapper.CreateClientMapper;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;
import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class ClientService {
    private static final ClientService INSTANCE = new ClientService();
    private final ClientDao clientDao = ClientDao.getInstance();
    private final CreateClientMapper createClientMapper = CreateClientMapper.getInstance();

    public List<ClientDto> findAll() {
        return clientDao.findAll().stream()
                .map(client -> ClientDto.builder()
                        .id(client.getId())
                        .userId(client.getUserId())
                        .age(client.getAge())
                        .licenceNo(client.getLicenceNo())
                        .validity(client.getValidity())
                        .build()
                )
                .collect(toList());
    }

    public void create(CreateClientDto createClientDto) {
        var client = createClientMapper.mapFrom(createClientDto);
        clientDao.add(client);
    }

    public Optional<Integer> findClientID(Integer userId) {
        return clientDao.findClientIdByUserId(userId);
    }

    public static ClientService getInstance() {
        return INSTANCE;
    }
}
